package cn.wyz.user.dto;


import cn.wyz.mapper.bean.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * (GroupMember)DTO
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GroupMemberDTO extends BaseDTO {

    private Long groupId;
    private Long userId;
    private Integer roleType;
    private Integer status;
    private LocalDateTime joinedAt;
}
