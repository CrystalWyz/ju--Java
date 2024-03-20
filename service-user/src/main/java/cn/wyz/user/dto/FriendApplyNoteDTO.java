package cn.wyz.user.dto;


import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.type.ApplyStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (FriendApplyNote)DTO
 *
 * @author zhouzhitong
 * @since 2024-03-25 23:05:55
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FriendApplyNoteDTO extends BaseDTO {

    private Long targetUserId;
    private String message;
    private ApplyStatus status;
    private String passNickName;
}
