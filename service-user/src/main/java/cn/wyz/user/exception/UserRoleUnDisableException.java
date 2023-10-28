package cn.wyz.user.exception;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;

/**
 * 角色不能够冻结异常
 *
 * @author zhouzhitong
 * @since 2021/9/10
 */
public class UserRoleUnDisableException extends BaseUserException {

    public UserRoleUnDisableException() {
        super(CodeConstant.USER_ROLE_DISABLE_ERROR);
    }

    public UserRoleUnDisableException(String roleName, String msg) {
        super(msg, CodeConstant.USER_ROLE_DISABLE_ERROR);
    }

}
