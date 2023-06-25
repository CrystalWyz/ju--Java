package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.bean.dto.TokenDTO;
import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.common.util.JwtUtils;
import cn.wyz.murdermystery.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @author wangnanxiang
 */
@Service
public class TokenServiceImpl implements TokenService {
    
    private final StringRedisTemplate stringRedisTemplate;

    public TokenServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public TokenResponseDTO refreshToken(String refreshToken) {

        // token解析
        TokenDTO tokenDTO = JwtUtils.parseToken(refreshToken);

        // 获取缓存中的token
        String refreshTokenKey = RedisKeyUtils.generatorTokenKey(tokenDTO.getPhone(), tokenDTO.getIdentity(),
                TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        // token 对比
        if (ObjectUtils.isEmpty(refreshTokenRedis) || !refreshTokenRedis.trim().equals(refreshToken)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "error token");
        }

        // 新token
        String accessToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        refreshToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());

        String accessTokenKey = RedisKeyUtils.generatorTokenKey(tokenDTO.getPhone(), IdentityEnum.PASSENGER.getCode(),TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 30, TimeUnit.DAYS);

        return TokenResponseDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
