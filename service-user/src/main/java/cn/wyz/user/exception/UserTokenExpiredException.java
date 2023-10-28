package cn.wyz.user.exception;

import cn.wyz.common.exception.BaseUserException;
import cn.wyz.common.exception.Code;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户异常类：用户请求没带 token请求失败
 *
 * @author zhouzhitong
 * @since 2021/8/28
 */
public class UserTokenExpiredException extends BaseUserException {

    /**
     * 有效时间
     */
    @Setter
    @Getter
    private String expiredTime;

    public UserTokenExpiredException(Code code) {
        super(null, code);
    }

    public UserTokenExpiredException(String message, Code code) {
        super(message, code);
    }

    public UserTokenExpiredException(String message, Code code, String expiredTime) {
        super(message, code);
        this.expiredTime = expiredTime;
    }

    @Override
    public String getMsg() {
        return getMessage();
    }
}
