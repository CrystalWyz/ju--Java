package cn.wyz.user.constant;

import cn.wyz.common.base.BaseEnum;
import cn.wyz.common.util.EnumUtils;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Getter
public enum Gender implements BaseEnum {

    BOY(1, "男"),
    GIRL(2, "女");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Gender of(Integer code) {
        return EnumUtils.codeOf(Gender.class, code);
    }


}
