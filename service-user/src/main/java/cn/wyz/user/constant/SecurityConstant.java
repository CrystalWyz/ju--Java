package cn.wyz.user.constant;

/**
 * @author zhouzhitong
 * @version 1.0
 * @since 2022/6/19
 */
public interface SecurityConstant {

    String USERNAME = "username";

    String NAME = "name";

    String PASSWORD = "password";

    String ROLE_NAME = "role";

    /**
     * 重定向地址
     */
    String LOCATION = "Location";

    /**
     * 用户IP
     */
    String IP = "ip";

    /**
     * 登录时间
     */
    String LOGIN_TIME = "loginTime";

    /**
     * token 请求头
     */
    String HEADER_PARAMETER = "Authorization";

    /**
     * 语言 请求头
     */
    String LANGUAGE = "lang";

    /**
     * 过期时间: 默认 60 天(单位: 毫秒)
     */
    long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000L * 60;

    /**
     * 密文
     */
    String SECRET = "secret";

}
