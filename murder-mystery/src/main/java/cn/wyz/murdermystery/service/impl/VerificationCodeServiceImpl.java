package cn.wyz.murdermystery.service.impl;

import cn.wyz.bean.response.TokenResponseDTO;
import cn.wyz.insternalcommon.bean.ResponseResult;
import cn.wyz.insternalcommon.bean.dto.TokenDTO;
import cn.wyz.insternalcommon.bean.response.NumberCodeResponse;
import cn.wyz.insternalcommon.constant.CommonStatusEnum;
import cn.wyz.insternalcommon.constant.IdentityEnum;
import cn.wyz.insternalcommon.constant.TokenTypeEnum;
import cn.wyz.insternalcommon.exception.AppException;
import cn.wyz.insternalcommon.util.JwtUtils;
import cn.wyz.insternalcommon.util.RedisKeyUtils;
import cn.wyz.remote.ServicePassengerUserClient;
import cn.wyz.remote.ServiceVerificationCodeClient;
import cn.wyz.service.VerificationCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangnanxiang
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final ServiceVerificationCodeClient serviceVerificationCodeClient;

    private final ServicePassengerUserClient servicePassengerUserClient;

    private final StringRedisTemplate stringRedisTemplate;

    public VerificationCodeServiceImpl(ServiceVerificationCodeClient serviceVerificationCodeClient, ServicePassengerUserClient servicePassengerUserClient, StringRedisTemplate stringRedisTemplate) {
        this.serviceVerificationCodeClient = serviceVerificationCodeClient;
        this.servicePassengerUserClient = servicePassengerUserClient;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void generatorVerificationCode(String passengerPhone) {

        // 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        Integer verificationCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        String key = RedisKeyUtils.generatorKey(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, String.valueOf(verificationCode), 5, TimeUnit.MINUTES);

        // 短信发送
    }

    @Override
    public TokenResponseDTO checkVerificationCode(String passengerPhone, String verificationCode) {
        // 验证码校验
        String key = RedisKeyUtils.generatorKey(passengerPhone);
        String redisCode = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(redisCode)) {
            throw new AppException(CommonStatusEnum.VERIFICATION_CODE_OVERDUE.getCode(), CommonStatusEnum.VERIFICATION_CODE_OVERDUE.getMessage());
        }

        if (!redisCode.equals(verificationCode.trim())) {
            throw new AppException(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }
        // 校验成功 删除验证码缓存
        stringRedisTemplate.delete(key);

        // 用户判断
        servicePassengerUserClient.login(passengerPhone);

        // 下发token
        TokenDTO tokenDTO = TokenDTO.builder().phone(passengerPhone).identity(IdentityEnum.PASSENGER.getCode()).build();
        String accessToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        String refreshToken = JwtUtils.generatorToken(tokenDTO, TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());

        //服务器端存储token
        String accessTokenKey = RedisKeyUtils.generatorTokenKey(passengerPhone, IdentityEnum.PASSENGER.getCode(),TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        String refreshTokenKey = RedisKeyUtils.generatorTokenKey(passengerPhone, IdentityEnum.PASSENGER.getCode(),TokenTypeEnum.REFRESH_TOKEN_TYPE.getCode());
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 30, TimeUnit.DAYS);

        return new TokenResponseDTO(accessToken, refreshToken);
    }
}
