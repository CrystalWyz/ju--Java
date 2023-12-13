package cn.wyz.common.util;

import cn.wyz.common.base.BaseEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author zhouzhitong
 * @since 2023/2/19
 */
@Slf4j
public class EnumUtils {

    public static <T extends BaseEnum> T codeOf(Class<T> c, Integer code) {
        if (code == null) {
            return null;
        }
        T[] enumConstants = c.getEnumConstants();
        for (T e : enumConstants) {
            if (Objects.equals(e.getCode(), code)) {
                return e;
            }
        }
        LOGGER.error("enum type {} don't have code {}.", c, code);
        return null;
    }

}
