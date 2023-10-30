package cn.wyz.user.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户, 角色, 权限层级关系: 用户 -> 角色 -> 权限
 *
 * @author zhouzhitong
 * @since 2022/10/1
 */
@Configuration
public class SecurityConfig {

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

}
