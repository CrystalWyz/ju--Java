package cn.wyz.user.type;

import cn.wyz.common.base.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2024-03-24
 **/

@AllArgsConstructor
@Getter
public enum ApplyStatus implements BaseEnum {

    APPLYING(0, "申请中"),
    ACCEPTED(1, "已接受"),
    REJECTED(2, "已拒绝");

    @JsonValue
    private final Integer code;

    private final String desc;

}
