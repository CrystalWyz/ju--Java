package cn.wyz.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * SpringDoc 配置类
 *
 * @author Jack魏
 * @since 2023/1/8 16:51
 */
@Configuration
public class OpenApiConfig {
    /**
     * SpringDoc 标题、描述、版本等信息配置
     *
     * @return openApi 配置信息
     */
    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("剧本杀 接口文档")
                        .description("剧本杀 接口文档")
                        .version("v0.0.1-SNAPSHOT")
                        .license(new License().name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }

    /**
     * 剧本杀相关
     *
     * @return 剧本杀相关
     */
    @Bean
    public GroupedOpenApi murderMystery() {
        return GroupedOpenApi.builder()
                .group("剧本杀相关")
                .pathsToMatch("/api/v1/murderMysteries/**")
                .build();
    }

    /**
     * 剧本杀用户相关
     *
     * @return 剧本杀用户相关
     */
    @Bean
    public GroupedOpenApi murderMysteryUser() {
        return GroupedOpenApi.builder()
                .group("剧本杀用户相关")
                .pathsToMatch("/api/v1/murderMysteryUser/**")
                .build();
    }

    @Bean
    public GroupedOpenApi murderMysteryTag() {
        return GroupedOpenApi.builder()
                .group("剧本杀标签相关")
                .pathsToMatch("/api/v1/murderMysteryTag/**")
                .build();
    }

    /**
     * 登录相关
     *
     * @return 剧本杀相关
     */
    @Bean
    public GroupedOpenApi authority() {
        return GroupedOpenApi.builder()
                .group("登录相关")
                .pathsToMatch("/api/v1/authorities/**")
                .build();
    }

    /**
     * 用户相关
     *
     * @return 用户相关
     */
    @Bean
    public GroupedOpenApi user() {
        return GroupedOpenApi.builder()
                .group("用户相关")
                .pathsToMatch("/api/v1/users/**")
                .build();
    }
}