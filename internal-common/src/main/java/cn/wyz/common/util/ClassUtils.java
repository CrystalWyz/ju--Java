package cn.wyz.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhouzhitong
 * @version 1.0
 * @since 2022/5/15 10:05
 */
@Slf4j
public class ClassUtils {

    public static Method getMethod(Class<?> c, String methodName, Class<?>[] parameterTypes) {
        try {
            return c.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            LOGGER.error("class {} resolver method {} has error {}.", c, methodName, e);
            return null;
        }
    }

    public static Method getReaderMethod(Class<?> c, Field field) {
        for (Method method : c.getDeclaredMethods()) {
            if (method.getParameterCount() != 0) {
                continue;
            }
            String methodName = method.getName();
            if (!methodName.equalsIgnoreCase("get" + field.getName())) {
                continue;
            }
            return method;
        }
        return null;
    }

    public static Method getReaderMethod(Class<?> c, String fieldName) {
        for (Method method : c.getDeclaredMethods()) {
            if (method.getParameterCount() != 0) {
                continue;
            }
            String methodName = method.getName();
            if (!methodName.equalsIgnoreCase("get" + fieldName)) {
                continue;
            }
            return method;
        }
        return null;
    }


    public static Method getWriterMethod(Class<?> c, Field field) {
        for (Method method : c.getDeclaredMethods()) {
            if (method.getParameterCount() != 1) {
                continue;
            }
            if (method.getParameterTypes()[0] != field.getType()) {
                continue;
            }
            String methodName = method.getName();
            if (!methodName.equalsIgnoreCase("set" + field.getName())) {
                continue;
            }
            return method;
        }
        return null;
    }

}
