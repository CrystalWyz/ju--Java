package cn.wyz.user.interceptor;

import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.AppException;
import cn.wyz.user.context.TokenInfo;
import cn.wyz.user.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author wangnanxiang
 */
//@Service
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        TokenInfo claims = JwtTokenUtils.parseToken(token);
        String username = claims.getUsername();
        String tokenRedis = stringRedisTemplate.opsForValue().get(username);
        if (StringUtils.isEmpty(tokenRedis)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        if (!token.trim().equals(tokenRedis.trim())) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        return true;
    }

}
