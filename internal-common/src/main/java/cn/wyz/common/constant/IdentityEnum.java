package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangnanxiang
 */
@Getter
@AllArgsConstructor
public enum IdentityEnum {

    /**
     * 用户
     */
    PASSENGER("1"),
    /**
     * 司机
     */
    DRIVER("2");

    private final String code;
}
