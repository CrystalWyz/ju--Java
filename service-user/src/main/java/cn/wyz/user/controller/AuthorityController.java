package cn.wyz.user.controller;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.user.bean.dto.LoginDTO;
import cn.wyz.user.bean.dto.OneClickLoginDTO;
import cn.wyz.user.bean.dto.UserTokenDTO;
import cn.wyz.user.bean.vo.UserInfoVO;
import cn.wyz.user.bean.vo.UserTokenVO;
import cn.wyz.user.converter.JuUserBeanConvert;
import cn.wyz.user.service.AuthorityService;
import cn.wyz.user.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 *
 * @author zhouzhitong
 * @since 2023/5/18
 */
@RestController
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    private final JuUserBeanConvert juUserBeanConvert;

    public AuthorityController(AuthorityService authorityService, JuUserBeanConvert juUserBeanConvert) {
        this.authorityService = authorityService;
        this.juUserBeanConvert = juUserBeanConvert;
    }

    /**
     * 一键登录(注册+登录)
     */
    @PostMapping("/oneClickLogin")
    public ResponseEntity<ResponseResult<UserTokenVO>> oneClickLogin(@Validated @RequestBody OneClickLoginDTO oneClickLoginDTO,
                                                                     HttpServletRequest request) {

        UserTokenDTO userTokenDTO = authorityService.oneClickLogin(oneClickLoginDTO);

        return ResponseEntity
                .status(200)
                .header("Location", request.getHeader("Location"))
                .body(ResponseResult.success(juUserBeanConvert.UserTokenDTOToUserTokenVO(userTokenDTO)));
    }

    /**
     * 登录
     *
     * @param param   登录参数
     * @param request 请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseResult<UserTokenVO>> login(@Validated @RequestBody LoginDTO param,
                                                             HttpServletRequest request) {
        UserTokenDTO login = authorityService.login(param);

        String location = request.getHeader("Location");
        return ResponseEntity
                .status(200)
                .header("Location", location)
                .body(ResponseResult.success(juUserBeanConvert.UserTokenDTOToUserTokenVO(login)));
    }

    /**
     * 刷新token
     *
     * @return 刷新结果
     */
    @PostMapping("/tokenRefresh")
    public ResponseResult<TokenResponseDTO> refreshToken() {
        TokenResponseDTO tokenResponseDTO = authorityService.refreshToken();
        return ResponseResult.success(tokenResponseDTO);
    }

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    @GetMapping
    public ResponseResult<UserInfoVO> getUserPermission() {
        return ResponseResult.success(authorityService.getCurrentUserInfo());
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @PutMapping("/logout")
    public ResponseEntity<?> logout() {
        String token = SecurityUtils.getToken();
        authorityService.logout(token);
        return ResponseEntity.ok().build();
    }
}
