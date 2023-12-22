package cn.wyz.murdermystery.type;

import cn.wyz.common.base.BaseEnum;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-12-21
 **/
@Getter
public enum UserStatus implements BaseEnum {


    ;

    private final Integer code;

    private final String desc;

    UserStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
