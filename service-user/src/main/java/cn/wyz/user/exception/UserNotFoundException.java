package cn.wyz.user.exception;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;

/**
 * 用户异常类：用户名找不到
 *
 * @author zhouzhitong
 * @since 2021/8/28
 */
public class UserNotFoundException extends BaseUserException {


    public UserNotFoundException() {
        super(CodeConstant.USERNAME_NOT_FOUND_ERROR);
    }

    public UserNotFoundException(String msg) {
        super(msg, CodeConstant.USERNAME_NOT_FOUND_ERROR);
    }
}
