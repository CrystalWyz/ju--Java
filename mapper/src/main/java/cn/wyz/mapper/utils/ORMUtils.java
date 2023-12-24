package cn.wyz.mapper.utils;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
public class ORMUtils {

    public static String getDbName(String fieldName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldName.length(); i++) {
            char c = fieldName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
