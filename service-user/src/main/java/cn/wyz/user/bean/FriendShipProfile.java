package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 好友列表关系属性表
 *
 * @author zhouzhitong
 * @since 2024-03-21
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "friend_ship_profile", resultMap = "FriendShipProfileMap")
public class FriendShipProfile extends BaseEntity {

    /**
     * 操作人Id
     *
     * @see User#getId()
     */
    private Long operatorId;

    /**
     * 朋友Id
     *
     * @see User#getId()
     */
    private Long friendId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 聊天是否顶置
     */
    private Boolean chatTop;

}
