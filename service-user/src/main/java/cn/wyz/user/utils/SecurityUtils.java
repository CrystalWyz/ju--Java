package cn.wyz.user.utils;

import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author zhouzhitong
 * @since 2022/9/20
 */
public class SecurityUtils {

    public static String getUsername() {
        LoginContext loginUser = SecurityContextHolder.getContext();
        return loginUser.getUsername();
    }

    public static String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (SecurityConstant.HEADER_PARAMETER.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}