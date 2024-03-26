package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendShipProfile) 请求参数
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendShipProfileQuery extends BaseRequest {

    private Long operatorId;
    private Long friendId;
    private String nickName;
    private Boolean chatTop;
}
