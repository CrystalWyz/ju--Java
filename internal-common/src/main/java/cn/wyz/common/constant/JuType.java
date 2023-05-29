package cn.wyz.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnx
 */

@Getter
@AllArgsConstructor
public enum JuType {

    /**
     * 剧本杀
     */
    MURDER_MYSTERY(0, "剧本杀"),
    /**
     * 其他
     */
    OTHER(999, "其他");

    private final Integer type;

    private final String name;
}
