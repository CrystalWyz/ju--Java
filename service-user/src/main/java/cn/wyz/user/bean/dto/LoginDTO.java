package cn.wyz.user.bean.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhouzhitong
 * @since 2023/5/21
 */
@Data
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

}
