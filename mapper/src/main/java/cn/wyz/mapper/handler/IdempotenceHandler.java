package cn.wyz.mapper.handler;

import cn.hutool.core.util.RandomUtil;
import cn.wyz.common.anno.Idempotence;
import cn.wyz.common.context.SystemContext;
import cn.wyz.common.exception.BaseException;
import cn.wyz.common.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 幂等性校验
 *
 * @author zhouzhitong
 * @see Idempotence
 * @since 2023-11-13
 **/
//@Component
//@Aspect
@Slf4j
@AllArgsConstructor
public class IdempotenceHandler {

    private final StringRedisTemplate redisTemplate;

    @Around("@annotation(cn.wyz.common.anno.Idempotence)")
    public void idempotence(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        Class declaringType = signature.getDeclaringType();
        String methodName = signature.getName();
        LOGGER.debug("declaringType: {}, methodName: {}", declaringType, methodName);
        // 获取当前操作者
        String currentAuditor = SystemContext.getAuditor();
        LOGGER.debug("currentAuditor: {}", currentAuditor);

        // 开始进行幂等性校验
        String key = declaringType + ":" + methodName + ":" + currentAuditor;
        if (!(signature instanceof MethodSignature methodSignature)) {
            throw new RuntimeException("Idempotence 注解只能用在方法上");
        }
        Object target = point.getTarget();
        Method method = ClassUtils.getMethod(target.getClass()
                , methodSignature.getName(), methodSignature.getParameterTypes());
        Idempotence idempotence = method.getAnnotation(Idempotence.class);
        long time = idempotence.time();
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, RandomUtil.randomString(10), time, TimeUnit.MILLISECONDS);
        if (Boolean.TRUE.equals(success)) {
            try {
                point.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new BaseException("请勿重复提交");
        }


    }

}
