package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-11-05
 **/

@Getter
@AllArgsConstructor
public enum JuInfoStatus implements BaseEnum {

    // 创建, 可以加入, 人数已满, 准备状态, 已开始, 已结束, 解散
    NEW(1, "创建", false),
    ENABLE_JOIN(2, "可以加入", true),
    FULL(3, "人数已满", false),
    // 下面的状态, 是不可以退出的
    PREPARE(4, "准备状态", false),
    STARTING(5, "已开始", false),
    END(6, "已结束", false),
    DISMISS(7, "解散", false);

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
    /**
     * 是否可以加入
     */
    private final Boolean enable_join;

    public boolean enableJoin() {
        return enable_join;
    }

    public boolean canOut() {
        return this == NEW || this == ENABLE_JOIN || this == FULL;
    }

}
