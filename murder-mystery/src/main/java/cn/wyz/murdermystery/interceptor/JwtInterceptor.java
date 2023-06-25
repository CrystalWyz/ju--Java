package cn.wyz.murdermystery.interceptor;

import cn.wyz.insternalcommon.bean.dto.TokenDTO;
import cn.wyz.insternalcommon.constant.CommonStatusEnum;
import cn.wyz.insternalcommon.constant.TokenTypeEnum;
import cn.wyz.insternalcommon.exception.AppException;
import cn.wyz.insternalcommon.util.JwtUtils;
import cn.wyz.insternalcommon.util.RedisKeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangnanxiang
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = JwtUtils.checkToken(token);

        String accessTokenKey = RedisKeyUtils.generatorTokenKey(tokenDTO.getPhone(), tokenDTO.getIdentity(), TokenTypeEnum.ACCESS_TOKEN_TYPE.getCode());
        String tokenRedis = stringRedisTemplate.opsForValue().get(accessTokenKey);
        if (StringUtils.isEmpty(tokenRedis)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        if (!token.trim().equals(tokenRedis.trim())) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        return true;
    }

}
