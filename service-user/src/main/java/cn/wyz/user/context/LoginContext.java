package cn.wyz.user.context;

import cn.wyz.common.context.SystemContext;
import cn.wyz.user.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @author zhouzhitong
 * @since 2021/8/31
 */
@Getter
@Setter
@ToString
public class LoginContext {

    private static final long serialVersionUID = -12312312345324L;

    private Long userId = -1L;

    /**
     * 账户名
     */
    private String username;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 账户当前使用的语言 (默认中文)
     */
    private String lang = SystemContext.getDefaultLocale();

    /**
     * 账户登录唯一标识
     */
    private String token;

    /**
     * 账户登录时间
     */
    private LocalDateTime loginTime;

    public LoginContext() {
    }

    public LoginContext(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public void setLang(String lang) {
        if (StringUtils.isNotBlank(lang)) {
            this.lang = lang;
        }
    }

    /**
     * 是否是登录用户
     *
     * @return true: 是登录用户, false: 匿名用户
     */
    public boolean isLoginUser() {
        return token != null && username != null;
    }

}
