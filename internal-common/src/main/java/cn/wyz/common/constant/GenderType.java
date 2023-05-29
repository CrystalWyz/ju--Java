package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnx
 */
@Getter
@AllArgsConstructor
public enum GenderType {

    /**
     * 小姐姐
     */
    GIRL(0, "小姐姐"),
    /**
     * 小哥哥
     */
    BOY(1, "小哥哥");

    private final Integer type;

    private final String name;
}
