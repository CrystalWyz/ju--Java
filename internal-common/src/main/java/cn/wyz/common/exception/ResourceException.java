package cn.wyz.common.exception;

import cn.wyz.common.constant.CodeConstant;

import java.io.Serial;

/**
 * 资源类异常
 *
 * @author zhouzhitong
 * @since 2023-11-11
 **/
public class ResourceException extends BaseException {

    @Serial
    private static final long serialVersionUID = -8993934800066999384L;

    public ResourceException() {
        this(CodeConstant.RESOURCE_REFRESH);
    }

    public ResourceException(String message) {
        this(message, CodeConstant.RESOURCE_REFRESH);
    }

    public ResourceException(Code code) {
        this(code.desc(), code);
    }

    protected ResourceException(String message, Code code) {
        super(message, code);
    }

}
