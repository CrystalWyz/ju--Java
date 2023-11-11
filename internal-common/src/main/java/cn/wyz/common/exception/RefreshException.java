package cn.wyz.common.exception;

import cn.wyz.common.constant.CodeConstant;

import java.io.Serial;

/**
 * @author zhouzhitong
 * @since 2023-11-11
 **/
public class RefreshException extends BaseException {

    @Serial
    private static final long serialVersionUID = -8993934800066999384L;

    public RefreshException() {
        this(CodeConstant.RESOURCE_REFRESH);
    }

    public RefreshException(String message) {
        this(message, CodeConstant.RESOURCE_REFRESH);
    }

    public RefreshException(Code code) {
        this(code.desc(), code);
    }

    protected RefreshException(String message, Code code) {
        super(message, code);
    }

}
