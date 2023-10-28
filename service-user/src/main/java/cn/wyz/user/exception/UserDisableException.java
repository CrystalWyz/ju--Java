package cn.wyz.user.exception;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;

/**
 * 账户冻结异常
 *
 * @author zhouzhitong
 * @since 2021/9/10
 */
public class UserDisableException extends BaseUserException {

    public UserDisableException() {
        super(CodeConstant.USER_DISABLE_ERROR);
    }

    public UserDisableException(String msg) {
        super(msg, CodeConstant.USER_DISABLE_ERROR);
    }

}
