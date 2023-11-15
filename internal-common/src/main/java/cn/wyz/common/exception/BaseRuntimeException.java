package cn.wyz.common.exception;

import cn.wyz.common.constant.CodeConstant;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @version 1.0
 * @since 2022/5/15 16:31
 */
public class BaseRuntimeException extends RuntimeException {

    @Getter
    private final Code code;

    public BaseRuntimeException() {
        this(CodeConstant.FAIL);
    }

    public BaseRuntimeException(String message) {
        this(message, CodeConstant.FAIL);
    }

    public BaseRuntimeException(Exception e) {
        super(e);
        if (e instanceof BaseRuntimeException baseException) {
            this.code = baseException.getCode();
        } else {
            this.code = CodeConstant.UN_KNOW_ERROR;
        }
    }

    public BaseRuntimeException(Code code) {
        this(code.desc(), code);
    }

    public BaseRuntimeException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public String getMsg() {
        return getMessage();
    }

    public Code code() {
        return this.code;
    }

}
