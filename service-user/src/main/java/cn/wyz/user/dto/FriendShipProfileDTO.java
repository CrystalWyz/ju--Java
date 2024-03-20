package cn.wyz.user.dto;


import cn.wyz.mapper.bean.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendShipProfile)DTO
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendShipProfileDTO extends BaseDTO {

    private Long operatorId;
    private Long friendId;
    private String nickName;
    private Boolean chatTop;
}
