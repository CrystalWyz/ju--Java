package cn.wyz.user.filter;

import cn.wyz.user.config.LibSecurityProperties;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.utils.JwtTokenUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhouzhitong
 * @since 2023/5/16
 */
@Component
@WebFilter(urlPatterns = "/**")
@Slf4j
public class SecurityFilter implements Filter {

    @Autowired
    private LibSecurityProperties securityProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        LoginContext loginContext = resolveToken((HttpServletRequest) request);
        if (loginContext != null) {
            SecurityContextHolder.setContext(loginContext);
        }

        loginContext = SecurityContextHolder.getContext();
        // 获取用户当前语言
        String lang = ((HttpServletRequest) request).getHeader(SecurityConstant.LANGUAGE);
        loginContext.setLang(lang);

        LOGGER.debug("current user {}", loginContext);

        SecurityContextHolder.setContext(loginContext);
        try {
            chain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private LoginContext resolveToken(HttpServletRequest request) {
        try {
            return JwtTokenUtils.getAuthentication(request);
        } catch (Exception e) {
            LOGGER.debug("resolve token error", e);
        }
        return null;
    }

}