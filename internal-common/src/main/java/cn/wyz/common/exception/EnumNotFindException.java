package cn.wyz.common.exception;

import cn.wyz.common.base.BaseEnum;
import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.constant.CommonStatusEnum;

/**
 * 枚举值异常
 *
 * @author zhouzhitong
 * @since 2023/2/19
 */
public class EnumNotFindException extends AppException {

    public EnumNotFindException(String message) {
        super(CommonStatusEnum.FAIL, message);
    }

    public static EnumNotFindException instant(Class<? extends BaseEnum> e, int code) {
        String msg = e + " 枚举值异常, 找不到code = " + code;
        return new EnumNotFindException(msg);
    }

}
