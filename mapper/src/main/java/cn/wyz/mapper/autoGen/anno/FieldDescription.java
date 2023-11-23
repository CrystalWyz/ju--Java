package cn.wyz.mapper.autoGen.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表字段描述
 *
 * @author zhouzhitong
 * @since 2023-11-20
 **/
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface FieldDescription {

    /**
     * 字段描述
     *
     * @return 字段描述
     */
    String comment();

    /**
     * 字段长度, 如果有长度的话需要填写, varchar默认64
     *
     * @return 字段长度
     */
    int length() default 0;

    /**
     * 默认值
     *
     * @return 默认值
     */
    String defaultValue() default "";

    /**
     * 是否可以为空
     *
     * @return 是否可以为空
     */
    boolean isAllowEmpty() default true;

}
