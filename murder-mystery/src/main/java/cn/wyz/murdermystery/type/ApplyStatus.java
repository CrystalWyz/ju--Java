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

    // 创建, 通过, 不通过
    NEW(1, "创建"),
    PASS(2, "通过"),
    NOT_PASS(3, "不通过");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;


}