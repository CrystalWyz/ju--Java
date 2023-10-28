package cn.wyz.user.vo;

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
public class UserRoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;


}
