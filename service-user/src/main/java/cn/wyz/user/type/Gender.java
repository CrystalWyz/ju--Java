package cn.wyz.user.type;

import cn.wyz.common.base.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Getter
public enum Gender implements BaseEnum {

    MAN(1, "男"),
    WOMAN(2, "女"),
    ;

    @JsonValue
    private final Integer code;

    private final String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
