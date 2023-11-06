package cn.wyz.common.bean.request;

import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.Code;
import lombok.Data;

/**
 * @author wangnanxiang
 */
@Data
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;

    private ResponseResult() {
    }

    protected ResponseResult(T data) {
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(CommonStatusEnum.SUCCESS.getCode());
        result.setMessage(CommonStatusEnum.SUCCESS.getMessage());

        return result;
    }

    public static <T> ResponseResult<T> ok(T data) {
        return success(data);
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(CommonStatusEnum.SUCCESS.getCode());
        result.setMessage(CommonStatusEnum.SUCCESS.getMessage());
        result.setData(data);

        return result;
    }

    public static ResponseResult<Void> fail(int code, String message) {
        ResponseResult<Void> result = new ResponseResult<>();
        result.setCode(code);
        result.setMessage(message);

        return result;
    }

    public static ResponseResult<Void> fail(Code code) {
        ResponseResult<Void> result = new ResponseResult<>();
        result.setCode(code.code());
        result.setMessage(code.desc());
        return result;
    }

    public static ResponseResult<Void> fail(Code code, String msg) {
        ResponseResult<Void> result = new ResponseResult<>();
        result.setCode(code.code());
        result.setMessage(msg);
        return result;
    }

    public static <T> ResponseResult<T> fail(int code, String message, T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);

        return result;
    }

    public static ResponseResult<?> fail(CommonStatusEnum status, String message) {
        return fail(status.getCode(), message);
    }

    public static <T> ResponseResult<T> fail(CommonStatusEnum status, String message, T data) {
        return fail(status.getCode(), message, data);
    }
}
