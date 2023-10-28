package cn.wyz.common.exception;

import cn.wyz.common.base.BaseEnum;
import cn.wyz.common.constant.CodeConstant;

/**
 * 枚举值异常
 *
 * @author zhouzhitong
 * @since 2023/2/19
 */
public class EnumNotFindException extends AppException {

    public EnumNotFindException(String message) {
        super(CodeConstant.ILLEGAL_PARAMETER_ERROR.code(), message);
    }

    public static EnumNotFindException instant(Class<? extends BaseEnum> e, int code) {
        String msg = e + " 枚举值异常, 找不到code = " + code;
        return new EnumNotFindException(msg);
    }

}
