package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendShip) 请求参数
 *
 * @author zhouzhitong
 * @since 2024-03-25 22:46:23
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendShipQuery extends BaseRequest {

    private Long userIdFrom;
    private Long userIdTo;
    private Integer friendType;
    private String nickName;
}
