package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnx
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * 上帝
     */
    LORD(0, "上帝"),
    /**
     * 商家
     */
    MERCHANT(1, "商家"),
    /**
     * 参与者
     */
    PARTICIPATOR(2, "参与者");

    private final Integer type;

    private final String name;
}
