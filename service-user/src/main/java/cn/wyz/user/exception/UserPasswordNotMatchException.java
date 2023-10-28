package cn.wyz.user.exception;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;

/**
 * 用户异常类：用户密码不正确
 *
 * @author zhouzhitong
 * @since 2021/8/28
 */
public class UserPasswordNotMatchException extends BaseUserException {

    public UserPasswordNotMatchException(String username) {
        super(CodeConstant.USERNAME_PASSWORD_NOT_FOUND_ERROR);
    }

    public UserPasswordNotMatchException(String username, String msg) {
        super(msg, CodeConstant.USERNAME_PASSWORD_NOT_FOUND_ERROR);
    }

}
