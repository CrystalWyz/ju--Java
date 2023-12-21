package cn.wyz.user.bean.vo;

import cn.wyz.user.constant.Gender;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户登录成功后返回的信息
 *
 * @author zhouzhitong
 * @since 2023/5/21
 */
@Data
public class UserTokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 重定向地址
     */
    private String location;

    /**
     * 性别
     */
    private Gender gender;
}
