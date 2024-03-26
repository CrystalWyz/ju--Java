package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.user.type.FriendType;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 好友关系表
 *
 * @author zhouzhitong
 * @since 2024-03-20
 **/
@Data
@TableName(value = "friend_ship", resultMap = "UserProfileMap")
public class FriendShip extends BaseEntity {

    /**
     * 关联用户ID, 好友申请发起人
     *
     * @see User#getId()
     */
    private Long userIdFrom;

    /**
     * 关联用户ID, 好友申请接受人
     *
     * @see User#getId()
     */
    private Long userIdTo;

    /**
     * 好友类型
     */
    private FriendType friendType;

    /**
     * 好友备注
     */
    private String nickName;

}
