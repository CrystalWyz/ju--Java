package cn.wyz.user.controller;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.dto.LoginDTO;
import cn.wyz.user.service.AuthorityService;
import cn.wyz.user.vo.UserInfoVO;
import cn.wyz.user.vo.UserTokenVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author zhouzhitong
 * @since 2023/5/18
 */
@RestController
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    @Resource
    private AuthorityService authorityService;

    /**
     * 登录
     *
     * @param param   登录参数
     * @param request 请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO param, HttpServletRequest request) {
        UserTokenVO login = authorityService.login(param);

        String location = request.getHeader("Location");
        return ResponseEntity
                .status(302)
                .header("Location", location)
                .body(ResponseResult.success(login));
    }

    /**
     * 刷新token
     *
     * @param request 请求
     * @return 刷新结果
     */
    @PostMapping("/tokenRefresh")
    public ResponseResult<TokenResponseDTO> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_PARAMETER);
        TokenResponseDTO tokenResponseDTO = authorityService.refreshToken(token);
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
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_PARAMETER);
        authorityService.logout(token);
        return ResponseEntity.ok().build();
    }

}
