package cn.wyz.user.type;

import cn.wyz.common.base.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 好友类型
 *
 * @author zhouzhitong
 * @since 2024-03-24
 **/
@AllArgsConstructor
@Getter
public enum FriendType implements BaseEnum {

    /**
     * 黑名单
     */
    BLACKLIST(-1, "黑名单"),

    /**
     * 普通好友
     */
    NORMAL(0, "普通好友"),

    /**
     * 特别关注
     */
    SPECIAL(1, "特别关注"),
    ;

    @JsonValue
    private final Integer code;

    private final String desc;

}
