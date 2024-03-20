package cn.wyz.user.dto;


import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.type.FriendType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendShip)DTO
 *
 * @author zhouzhitong
 * @since 2024-03-25 22:46:23
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendShipDTO extends BaseDTO {

    private Long userIdFrom;
    private Long userIdTo;
    private FriendType friendType;
    private String nickName;
}
