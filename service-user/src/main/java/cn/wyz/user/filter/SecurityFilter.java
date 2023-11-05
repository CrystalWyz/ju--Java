package cn.wyz.user.filter;

import cn.wyz.user.config.LibSecurityProperties;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.utils.JwtTokenUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
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
            // ip 检验
//            if (securityProperties.isCheckIp()) {
//                String ip = loginContext.getIp();
//                String ipAddr = IpUtils.getIpAddr((HttpServletRequest) request);
//                if (!StringUtils.equals(ip, ipAddr)) {
//                    LOGGER.warn("ip 不一致, token {} ip: {}, request ip: {}", loginContext, ip, ipAddr);
//                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "token 无效, 请重新登录");
//                    return;
//                }
//            }
            SecurityContextHolder.setContext(loginContext);
        }

        loginContext = SecurityContextHolder.getContext();
        // 获取用户当前语言
        String lang = ((HttpServletRequest) request).getHeader(SecurityConstant.LANGUAGE);
        loginContext.setLang(lang);

        LOGGER.trace("current user {}", loginContext);

        SecurityContextHolder.setContext(loginContext);
        try {
            chain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private LoginContext resolveToken(HttpServletRequest request) {
        return JwtTokenUtils.getAuthentication(request);
    }

}