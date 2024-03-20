package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.user.type.ApplyStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 1. createdBy(发起者) -> targetUserId(受邀者) 发起好友申请.
 * <p>
 * 2. targetUserId -> createdBy 通过好友申请, 两人成为好友
 *
 * @author zhouzhitong
 * @since 2024-03-24
 **/
@TableName(value = "\"friend_apply_note\"", resultMap = "FriendApplyNoteMap")
@Data
public class FriendApplyNote extends BaseEntity {

    /**
     * 受邀者: 目标用户ID
     *
     * @see User#getId()
     */
    private Long targetUserId;

    /**
     * 申请发送消息
     */
    private String message;

    /**
     * 申请通过后的好友昵称
     */
    private String passNickName;

    /**
     * 申请状态
     */
    private ApplyStatus status;

}
