package cn.wyz.user.interceptor;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;
import cn.wyz.user.config.LibSecurityProperties;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.exception.UserTokenExpiredException;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.TokenService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author wangnanxiang
 */
@Service
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Resource
    private LibSecurityProperties securityProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (ignore(request)) {
            LOGGER.debug("ignore url {}", request.getRequestURI());
            return true;
        }
        LoginContext context = SecurityContextHolder.getContext();
        if (!context.isLoginUser()) {
            throw new UserTokenExpiredException();
        }
        String username = context.getUsername();
        String tokenRedis = tokenService.getToken(username);
        String token = context.getToken();
        if (StringUtils.isEmpty(tokenRedis) || !token.trim().equals(tokenRedis.trim())) {
            throw new BaseUserException(CodeConstant.USER_TOKEN_INVALID_ERROR);
        }
        return true;
    }

    /**
     * 判断是否是忽略的url
     *
     * @param request current HTTP request
     * @return true: 忽略, false: 不忽略
     */
    private boolean ignore(HttpServletRequest request) {
        String loginUrl = securityProperties.getLoginUrl();
        AntPathMatcher matcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        if (matcher.match(loginUrl, uri)) {
            return true;
        }
        List<String> ignoreUrls = securityProperties.getIgnoreUrls();
        if (CollectionUtils.isNotEmpty(ignoreUrls)) {
            for (String ignoreUrl : ignoreUrls) {
                if (matcher.match(ignoreUrl, uri)) {
                    return true;
                }
            }
        }
        return false;
    }

}
