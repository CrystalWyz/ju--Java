package cn.wyz.common.util;

import cn.wyz.common.base.BaseEnum;
import cn.wyz.common.exception.EnumNotFindException;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举工具类
 *
 * @author zhouzhitong
 * @since 2023/2/19
 */
@Slf4j
public class EnumUtils {

    public static <T extends BaseEnum> T codeOf(Class<T> c, int code) {
        T[] enumConstants = c.getEnumConstants();
        for (T e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        LOGGER.error("enum type {} don't have code {}.", c, code);
        throw EnumNotFindException.instant(c, code);
    }

}
