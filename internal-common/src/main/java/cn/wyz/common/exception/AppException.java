package cn.wyz.common.exception;

import cn.wyz.common.constant.CommonStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangnanxiang
 */
@Data
@NoArgsConstructor
public class AppException extends RuntimeException {


    private CommonStatusEnum commonStatusEnum;

    private String message;

    public AppException(CommonStatusEnum commonStatusEnum, String message) {
        super(message);
        this.commonStatusEnum = commonStatusEnum;
        this.message = message;
    }
}
