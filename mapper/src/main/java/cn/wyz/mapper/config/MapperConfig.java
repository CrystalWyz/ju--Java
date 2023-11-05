package cn.wyz.mapper.config;

import cn.wyz.mapper.handler.ListTypeHandler;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Configuration
@PropertySource("classpath:mybatis-config.properties")
//@EnableConfigurationProperties(MybatisProperties.class)
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class MapperConfig {

//    static {
//        LOGGER.info("MybatisPlusConfig init enum type handler: {}",
//                DefaultEnumTypeHandler.class.getName());
//        CompositeEnumTypeHandler.setDefaultEnumTypeHandler(DefaultEnumTypeHandler.class);
//    }

    @Autowired
    private MybatisProperties properties;

    /**
     * 配置MybatisPlus分页插件
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            // 注册自定义的TypeHandler
            configuration.getTypeHandlerRegistry().register(ListTypeHandler.class);
        };
    }

}
