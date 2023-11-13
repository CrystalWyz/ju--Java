package cn.wyz.common.anno;

import java.lang.annotation.*;

/**
 * 幂等性注解
 *
 * @author zhouzhitong
 * @since 2023-11-12
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Idempotence {

    /**
     * 幂等间隔时间, 单位: 毫秒[ms]
     *
     * @return time, 单位: 毫秒[ms]
     */
    long time() default 1000L;

}
