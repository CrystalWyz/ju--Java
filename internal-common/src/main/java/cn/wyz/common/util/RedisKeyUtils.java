package cn.wyz.common.util;

/**
 * @author wangnanxiang
 */
public class RedisKeyUtils {

    private static final String VERIFICATION_CODE_PREFIX = "ju-verification-code-";

    private static final String TOKEN_PREFIX = "token-";

    private static final String SEPARATOR = "@@";

    public static String generatorKey(String passengerPhone) {
        return VERIFICATION_CODE_PREFIX + passengerPhone;
    }

    public static String generatorTokenKey(String phone, String identity, String tokenType) {
        return TOKEN_PREFIX + phone + SEPARATOR + identity + "-" + tokenType + "-";
    }
}
