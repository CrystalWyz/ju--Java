package cn.wyz.user.interceptor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangnanxiang
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Bean
//    public JwtInterceptor jwtInterceptor() {
//        return new JwtInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/verification-code")
//                .excludePathPatterns("/verification-code-check")
//                .excludePathPatterns("/token-refresh");
//    }
}