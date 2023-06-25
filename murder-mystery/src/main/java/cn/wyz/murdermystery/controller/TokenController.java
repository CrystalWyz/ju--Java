package cn.wyz.murdermystery.controller;

import cn.wyz.bean.request.RefreshTokenDTO;
import cn.wyz.bean.response.TokenResponseDTO;
import cn.wyz.insternalcommon.bean.ResponseResult;
import cn.wyz.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangnanxiang
 */
@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token-refresh")
    public ResponseResult<TokenResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        TokenResponseDTO tokenResponseDTO = tokenService.refreshToken(refreshTokenDTO.getRefreshToken());
        return ResponseResult.success(tokenResponseDTO);
    }
}
