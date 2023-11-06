package cn.wyz.user.config;

import cn.wyz.user.interceptor.JwtInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用户, 角色, 权限层级关系: 用户 -> 角色 -> 权限
 *
 * @author zhouzhitong
 * @since 2022/10/1
 */
@Configuration
@ComponentScan("cn.wyz.user")
@Slf4j
public class SecurityConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    /**
     * 默认加密方式
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("add interceptors");
        registry.addInterceptor(jwtInterceptor);
    }

}
