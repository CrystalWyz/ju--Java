package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * (GroupMember) 请求参数
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GroupMemberQuery extends BaseRequest {

    private Long groupId;
    private Long userId;
    private Integer roleType;
    private Integer status;
    private LocalDateTime joinedAt;
}
