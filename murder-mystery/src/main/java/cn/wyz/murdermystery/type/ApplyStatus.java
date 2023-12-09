package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 申请状态
 *
 * @author zhouzhitong
 * @since 2023-11-06
 **/
@Getter
@AllArgsConstructor
public enum ApplyStatus implements BaseEnum {

    // 撤销, 创建, 通过, 不通过, 失效
    INVALID(-3, "失效"),
    NOT_PASS(-2, "不通过"),
    CANCEL(-1, "撤销"),
    NEW(1, "创建"),
    PASS(2, "通过"),
    ;

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;


}
