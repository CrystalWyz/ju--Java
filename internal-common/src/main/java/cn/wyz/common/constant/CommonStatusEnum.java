package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnx
 */

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    FAIL(500, "fail"),

    /**
     * 失败并刷新, 用于前端刷新页面
     */
    FAIL_REFRESH(301, "fail and refresh"),
    ;


    private final Integer code;

    private final String message;
}
