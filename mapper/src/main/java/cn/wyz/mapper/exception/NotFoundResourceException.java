package cn.wyz.mapper.exception;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseRuntimeException;

/**
 * @author zhouzhitong
 * @since 2023-11-14
 **/
public class NotFoundResourceException extends BaseRuntimeException {

    public NotFoundResourceException() {
        super(CodeConstant.RESOURCE_NOT_FOUND);
    }

    public NotFoundResourceException(String message) {
        super(message, CodeConstant.RESOURCE_NOT_FOUND);
    }
}
