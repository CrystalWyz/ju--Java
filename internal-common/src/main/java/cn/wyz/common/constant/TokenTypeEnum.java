package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangnanxiang
 */
@AllArgsConstructor
@Getter
public enum TokenTypeEnum {

    /**
     * accessToken
     */
    ACCESS_TOKEN_TYPE("accessToken"),
    /**
     * refreshToke
     */
    REFRESH_TOKEN_TYPE("refreshToke");

    private final String code;
}
