package cn.wyz.user.utils;

import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;

/**
 * @author zhouzhitong
 * @since 2022/9/20
 */
public class SecurityUtils {

    public static String getUsername() {
        LoginContext loginUser = SecurityContextHolder.getContext();
        return loginUser.getUsername();
    }

}
