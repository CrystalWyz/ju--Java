package cn.wyz.common.exception;

import cn.wyz.common.constant.CodeConstant;

import java.io.Serial;

/**
 * @author zhouzhitong
 * @since 2023-12-09
 **/
public class BaseRefreshException extends BaseRuntimeException {

    @Serial
    private static final long serialVersionUID = -8993934800066999384L;

    public BaseRefreshException() {
        this(CodeConstant.RESOURCE_REFRESH);
    }

    public BaseRefreshException(String message) {
        this(message, CodeConstant.RESOURCE_REFRESH);
    }

    public BaseRefreshException(Code code) {
        this(code.desc(), code);
    }

    public BaseRefreshException(String message, Code code) {
        super(message, code);
    }

}
