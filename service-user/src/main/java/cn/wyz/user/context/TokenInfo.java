package cn.wyz.user.context;

import cn.wyz.user.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-10-30
 **/
@Getter
@AllArgsConstructor
@Builder
public class TokenInfo {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 用户登录 IP
     */
    private String ip;

    /**
     * 用户登录 token
     */
    private String token;

    /**
     * token 创建时间
     */
    private Long tokenCreateTime;

}
