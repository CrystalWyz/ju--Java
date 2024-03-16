package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 剧本杀游戏状态
 * <pre>
 *     DRAFT  --> NEW  -->  PREPARE  -->  SIGN_IN  -->  STARTING  -->  END
 *      |          |
 *      |----------|-->  DISMISS
 *
 * </pre>
 *
 * @author zhouzhitong
 * @since 2023-11-05
 **/
@Getter
@AllArgsConstructor
public enum GameStatus implements BaseEnum {

    DISMISS(-2, "解散"),
    END(-1, "已结束"),
    // 草稿, 创建, 人数已满, 准备, 签到, 已开始, 已结束, 解散
    DRAFT(1, "草稿"),
    NEW(2, "创建"),
    // 下面的状态, 是不可以退出的
    PREPARE(3, "准备"),
    SIGN_IN(4, "签到"),
    STARTING(5, "已开始"),

    ;

    /**
     * 状态码
     */
    @EnumValue
    private final Integer code;

    /**
     * 描述
     */
    @JsonValue
    private final String desc;

    public boolean enableJoin() {
        return NEW == this;
    }

    public boolean canOut() {
        return this == NEW;
    }

}
