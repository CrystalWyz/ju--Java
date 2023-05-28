package cn.wyz.murdermystery.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wnx
 */
@Configuration
public class FastjsonConfig implements WebMvcConfigurer {
    /**
     * 配置fastjson输出格式
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1. 配置fastjson
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setCharset(StandardCharsets.UTF_8);

        // 2. 添加fastjson转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();

        // 3. 添加支持的媒体类型
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(supportedMediaTypes);

        //4 将convert添加到converters
        converter.setFastJsonConfig(config);
        converters.add(0, converter);
    }
}