package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Getter
public enum ServiceType implements BaseEnum {

    MurderMystery(1, "剧本杀"),
    ;

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;

    ServiceType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
