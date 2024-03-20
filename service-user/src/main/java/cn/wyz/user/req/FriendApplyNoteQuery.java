package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendApplyNote) 请求参数
 *
 * @author zhouzhitong
 * @since 2024-03-25 23:05:59
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendApplyNoteQuery extends BaseRequest {

    /**
     * 受邀者
     */
    private Long targetUserId;

    /**
     * 发起者
     */
    private Long createdBy;

    private String message;

    /**
     * 受邀者通过之后, 给对方的昵称( tToC)
     */
    private String nickName;

    /**
     * 发起者在受邀者通过后, 设置的好友昵称( cToT)
     */
    private String passNickName;
}
