package cn.wyz.user.service;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
public interface TokenService {

    /**
     * 获取 token
     *
     * @param username 用户名
     * @return token
     */
    String getToken(String username);

    /**
     * 保存或刷新 token
     *
     * @param username 用户名
     * @param token    token
     * @return token
     */
    String saveOrRefreshToken(String username, String token);

    /**
     * 删除 token
     *
     * @param username 用户名
     * @return 是否删除成功
     */
    boolean deleteToken(String username);

}
