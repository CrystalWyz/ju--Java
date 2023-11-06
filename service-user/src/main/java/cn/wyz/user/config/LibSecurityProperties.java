package cn.wyz.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouzhitong
 * @since 2023/5/16
 */
@Component
@ConfigurationProperties(prefix = "ju.security")
@Data
public class LibSecurityProperties {

    /**
     * 登录请求接口
     */
    private String loginUrl = "/**/login";

    /**
     * 登出请求接口
     */
    private String logoutUrl = "/**/logout";

    /**
     * 登录成功后默认跳转的页面
     */
    private String location = "http://localhost:8080/index.html";

    /**
     * 忽略的请求接口
     */
    private List<String> ignoreUrls;

    /**
     * 是否校验IP(防止其他人拿到token后, 在其他电脑上进行非法操作)
     */
    private boolean checkIp = true;

    /**
     * 超级管理员账号
     */
    private String username = "supperAdmin";

    /**
     * 超级管理员密码
     */
    private String password = "ju123456";


}
