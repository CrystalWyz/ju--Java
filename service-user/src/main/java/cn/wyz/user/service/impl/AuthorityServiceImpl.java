package cn.wyz.user.service.impl;

import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.AppException;
import cn.wyz.common.exception.BaseUserException;
import cn.wyz.common.util.EncryptUtils;
import cn.wyz.user.config.LibSecurityProperties;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.context.TokenInfo;
import cn.wyz.user.converter.JuUserBeanConvert;
import cn.wyz.user.dto.LoginDTO;
import cn.wyz.user.dto.OneClickLoginDTO;
import cn.wyz.user.dto.UserDTO;
import cn.wyz.user.dto.UserTokenDTO;
import cn.wyz.user.exception.UserDisableException;
import cn.wyz.user.exception.UserNotFoundException;
import cn.wyz.user.exception.UserPasswordNotMatchException;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.AuthorityService;
import cn.wyz.user.service.TokenService;
import cn.wyz.user.service.UserService;
import cn.wyz.user.utils.JwtTokenUtils;
import cn.wyz.user.utils.SecurityUtils;
import cn.wyz.user.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Service
@Slf4j
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final LibSecurityProperties libSecurityProperties;

    private final TokenService tokenService;

    private final StringRedisTemplate redisTemplate;

    private final JuUserBeanConvert convert;

    @Override
    public UserTokenDTO login(LoginDTO param) {
        LOGGER.debug("login param username: {}", param.getUsername());
        String username = param.getUsername();
        String rawPassword = param.getPassword();
        // 获取用户
        UserDTO user = getUser(username);
        // 如果没有找到就抛出异常
        if (user == null) {
            throw new UserNotFoundException();
        }
        // 检查用户是否是超级管理员
        if (!user.getIsSupperAdmin()) {
            // 检查用户是否被禁用
            if (!user.getEnabled()) {
                throw new UserDisableException();
            }
        }

        // 加密处理
        String encrypt = EncryptUtils.encrypt16(rawPassword);
        String password = user.getPassword();
        if (password == null) {
            throw new BaseUserException("当前账户没设置密码, 请使用手机号设置密码登录");
        }

        // 匹配密码
        String encodePwd = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(encrypt, encodePwd)) {
            throw new UserPasswordNotMatchException(username);
        }
        return generatorToken(user);
    }

    private UserTokenDTO generatorToken(UserDTO user) {
        String token = tokenService.getToken(user.getUsername());
        if (StringUtils.isEmpty(token)) {
            TokenInfo tokenInfo = TokenInfo.builder()
                    .userId(user.getId())
                    .username(user.getUsername())
                    .gender(user.getGender())
                    .build();
            token = JwtTokenUtils.generatorToken(tokenInfo);
            // 不管怎样刷新下 token
            tokenService.saveOrRefreshToken(user.getUsername(), token);
        }


        UserTokenDTO res = new UserTokenDTO();
        res.setUsername(user.getUsername());
        res.setToken(token);
        res.setExpireTime(System.currentTimeMillis() + JwtTokenUtils.getExpiration());
        res.setUser(user);
        return res;
    }

    @Override
    public TokenResponseDTO refreshToken() {
        LoginContext context = SecurityContextHolder.getContext();
        String refreshToken = context.getToken();
        TokenInfo claims = JwtTokenUtils.parseToken(refreshToken);
        String username = claims.getUsername();
        String newToken = JwtTokenUtils.generatorToken(claims);
        tokenService.saveOrRefreshToken(username, newToken);
        return new TokenResponseDTO(newToken, refreshToken);
    }

    @Override
    public void logout(String token) {
        LOGGER.info("logout token: {}", token);
        TokenInfo claims = JwtTokenUtils.parseToken(token);
        String username = claims.getUsername();
        tokenService.deleteToken(username);
    }

    @Override
    public UserInfoVO getCurrentUserInfo() {
        String token = SecurityUtils.getToken();
        if (token == null) {
            throw new UserNotFoundException();
        }
        TokenInfo claims = JwtTokenUtils.parseToken(token);
        String username = claims.getUsername();
        UserDTO user = getUser(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!user.getEnabled()) {
            throw new UserDisableException();
        }
        UserInfoVO userRoleVO = new UserInfoVO();
        userRoleVO.setUsername(username);
        userRoleVO.setName(user.getName());
        return userRoleVO;
    }


    private UserDTO getUser(String username) {
        UserDTO user = tryGetAdmin(username);
        if (user != null) {
            return null;
        }
        user = userService.queryByUsername(username);
        return user;
    }

    private UserDTO tryGetAdmin(String username) {
        if (!StringUtils.equals(libSecurityProperties.getUsername(), username)) {
            return null;
        }
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(libSecurityProperties.getPassword());
        user.setIsSupperAdmin(true);
        return user;
    }

    @Override
    public UserTokenDTO oneClickLogin(OneClickLoginDTO oneClickLoginDTO) {
        LOGGER.debug("一键登录: {}", oneClickLoginDTO);
        // 校验码验证
        String verifyCode = redisTemplate.opsForValue().get(oneClickLoginDTO.getPhone());
        if (ObjectUtils.isEmpty(verifyCode)) {
            throw new AppException(CommonStatusEnum.FAIL, "验证码丢失");
        }
        if (!verifyCode.equals(oneClickLoginDTO.getVerifyCode())) {
            throw new AppException(CommonStatusEnum.FAIL, "验证码错误");
        }

        // 手机号获取用户信息
        UserDTO userInfo = userService.getByPhone(oneClickLoginDTO.getPhone());

        if (ObjectUtils.isEmpty(userInfo)) {
            // 没有就自动注册
            userInfo = userService.register(oneClickLoginDTO.getPhone());
        }

        return generatorToken(userInfo);
    }

    @Override
    public void register(LoginDTO param) {

    }
}
