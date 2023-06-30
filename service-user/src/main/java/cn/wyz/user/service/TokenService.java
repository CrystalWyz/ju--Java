package cn.wyz.user.service;

import cn.wyz.common.bean.response.TokenResponseDTO;

/**
 * @author wangnanxiang
 */
public interface TokenService {

    /**
     * 刷新token
     * @param refreshToken refreshToken
     * @return new accessToken
     */
    TokenResponseDTO refreshToken(String refreshToken);
}