package cn.wyz.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangnanxiang
 */
@Data
@NoArgsConstructor
public class AppException extends RuntimeException {


    private Integer code;

    private String message;

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
