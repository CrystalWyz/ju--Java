package cn.wyz.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zhouzhitong
 * @since 2024-03-17
 **/
@Configuration
public class WebFileConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        System.out.println("self WebFileConfig");
        return new WebMvcConfigurer() {
            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new ByteArrayHttpMessageConverter());
            }
        };
    }

}
