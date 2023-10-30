package cn.wyz.user.service.impl;

import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Service
@Slf4j
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final StringRedisTemplate redisTemplate;

    @Override
    public String getToken(String username) {
        String token = redisTemplate.opsForValue().get(username);
        return token;
    }

    @Override
    public String saveOrRefreshToken(String username, String token) {
        LOGGER.debug("saveOrRefreshToken username: {}, token: {}", username, token);
        long tokenExpiration = SecurityConstant.TOKEN_EXPIRATION;
        redisTemplate.opsForValue().set(username, token, tokenExpiration, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public boolean deleteToken(String username) {
        LOGGER.debug("deleteToken username: {}", username);
        return Boolean.TRUE.equals(redisTemplate.delete(username));
    }
}
