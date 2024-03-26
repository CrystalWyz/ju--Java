package cn.wyz.user.type;

import cn.wyz.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2024-03-25
 **/
@Getter
@AllArgsConstructor
public enum GroupRole implements BaseEnum {

    // 群主, 管理员, 普通成员
    MEMBER(0, "普通成员"),
    OWNER(1, "群主"),
    ADMIN(2, "管理员"),
    ;

    private final Integer code;

    private final String desc;

}
