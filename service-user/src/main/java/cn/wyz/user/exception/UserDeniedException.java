package cn.wyz.user.exception;


import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;

/**
 * @author zhouzhitong
 * @since 2023/6/1
 */
public class UserDeniedException extends BaseUserException {

    public UserDeniedException() {
        super(CodeConstant.USER_TOKEN_DENIED_PERMISSION);
    }
}
