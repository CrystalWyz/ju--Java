package cn.wyz.user.service.impl;

import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.AppException;
import cn.wyz.common.util.EncryptUtils;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.bo.OneClickLoginBO;
import cn.wyz.user.bean.bo.UserBO;
import cn.wyz.user.bean.bo.UserTokenBO;
import cn.wyz.user.bean.dto.LoginDTO;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.bean.vo.UserInfoVO;
import cn.wyz.user.config.LibSecurityProperties;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.context.TokenInfo;
import cn.wyz.user.converter.JuUserBeanConvert;
import cn.wyz.user.exception.UserDisableException;
import cn.wyz.user.exception.UserNotFoundException;
import cn.wyz.user.exception.UserPasswordNotMatchException;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.AuthorityService;
import cn.wyz.user.service.TokenService;
import cn.wyz.user.service.UserService;
import cn.wyz.user.utils.JwtTokenUtils;
import cn.wyz.user.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
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
    public UserTokenBO login(LoginDTO param) {
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

        // 匹配密码
        String encodePwd = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(encrypt, encodePwd)) {
            throw new UserPasswordNotMatchException(username);
        }

        return generatorToken(convert.userDTOToUserBO(user));
    }

    private UserTokenBO generatorToken(UserBO user) {
        String token;
        if ((token = tokenService.getToken(user.getUsername())) == null) {
            TokenInfo tokenInfo = TokenInfo.builder()
                    .userId(user.getId())
                    .username(user.getUsername())
                    .gender(user.getGender())
                    .build();
            token = JwtTokenUtils.generatorToken(tokenInfo);
            // 不管怎样刷新下 token
            tokenService.saveOrRefreshToken(user.getUsername(), token);
        }


        UserTokenBO res = new UserTokenBO();
        res.setUsername(user.getUsername());
        res.setToken(token);
        res.setExpireTime(System.currentTimeMillis() + JwtTokenUtils.getExpiration());
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
    public UserTokenBO oneClickLogin(OneClickLoginBO oneClickLoginBO) {

        // 校验码验证
        String verifyCode = redisTemplate.opsForValue().get(oneClickLoginBO.getPhone());
        if (ObjectUtils.isEmpty(verifyCode)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "验证码丢失");
        }
        if (!verifyCode.equals(oneClickLoginBO.getVerifyCode())) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "验证码错误");
        }

        // 手机号获取用户信息
        UserBO userInfo = userService.getByPhone(oneClickLoginBO.getPhone());
        if (ObjectUtils.isEmpty(userInfo)) {
            // 注册
            User user = new User();
            user.setPhone(oneClickLoginBO.getPhone());
            user.setNickName("刁民-" + RandomUtils.nextLong(0,Long.MAX_VALUE));

            userService.save(user);
        }

        return generatorToken(userInfo);
    }
}
