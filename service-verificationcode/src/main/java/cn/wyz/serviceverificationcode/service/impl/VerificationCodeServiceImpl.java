package cn.wyz.serviceverificationcode.service.impl;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.common.bean.dto.TokenDTO;
import cn.wyz.common.bean.response.NumberCodeResponse;
import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.constant.IdentityEnum;
import cn.wyz.common.constant.TokenTypeEnum;
import cn.wyz.common.exception.AppException;
import cn.wyz.common.util.JwtUtils;
import cn.wyz.common.util.RedisKeyUtils;
import cn.wyz.serviceverificationcode.remote.UserFeign;
import cn.wyz.serviceverificationcode.remote.VerificationCodeFeign;
import cn.wyz.serviceverificationcode.service.VerificationCodeService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangnanxiang
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final UserFeign userFeign;

    private final StringRedisTemplate stringRedisTemplate;

    private final VerificationCodeFeign verificationCodeFeign;

    public VerificationCodeServiceImpl(UserFeign userFeign, StringRedisTemplate stringRedisTemplate,
                                       VerificationCodeFeign verificationCodeFeign) {
        this.userFeign = userFeign;
        this.stringRedisTemplate = stringRedisTemplate;
        this.verificationCodeFeign = verificationCodeFeign;
    }

    @Override
    public void generatorVerificationCode(String passengerPhone) {

        // 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = verificationCodeFeign.getNumberCode(6);
        Integer verificationCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        String key = RedisKeyUtils.generatorKey(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, String.valueOf(verificationCode), 5, TimeUnit.MINUTES);

        // 短信发送
    }

    @Override
    public TokenResponseDTO checkVerificationCode(String userPhone, String verificationCode) {
        // 验证码校验
        String key = RedisKeyUtils.generatorKey(userPhone);
        String redisCode = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(redisCode)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), CommonStatusEnum.FAIL.getMessage());
        }

        if (!redisCode.equals(verificationCode.trim())) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), CommonStatusEnum.FAIL.getMessage());
        }
        // 校验成功 删除验证码缓存
        stringRedisTemplate.delete(key);

        // 用户存在判断
        if (ObjectUtils.isEmpty(userFeign.userDetailByPhone(userPhone))) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "用户不存在");
        }

        // 下发token
        TokenDTO tokenDTO = TokenDTO.builder().phone(userPhone).identity(IdentityEnum.PASSENGER.getCode()).build();
        String accessToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        String refreshToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());

        //服务器端存储token
        String accessTokenKey = RedisKeyUtils.generatorTokenKey(userPhone, IdentityEnum.PASSENGER.getCode(),TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        String refreshTokenKey = RedisKeyUtils.generatorTokenKey(userPhone, IdentityEnum.PASSENGER.getCode(),TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 30, TimeUnit.DAYS);

        return new TokenResponseDTO(accessToken, refreshToken);
    }
}
