package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-12-09
 **/
@Getter
public enum BlemishDetailType implements BaseEnum {


    // 1. 迟到, 2. 缺席, 3. 早退
    LATE(1, "迟到"),
    ABSENT(2, "缺席"),
    LEAVE_EARLY(3, "早退"),

    ;


    private final Integer code;
    private final String desc;


    BlemishDetailType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
