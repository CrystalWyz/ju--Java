package cn.wyz.user.exception;

import cn.wyz.common.exception.BaseUserException;
import cn.wyz.common.exception.Code;

/**
 * 用户异常类：用户请求没带 token请求失败
 *
 * @author zhouzhitong
 * @since 2021/8/28
 */
public class UserNotTokenException extends BaseUserException {

    public UserNotTokenException(String message, Code code) {
        super(message, code);
    }

    @Override
    public String getMsg() {
        return null;
    }
}
