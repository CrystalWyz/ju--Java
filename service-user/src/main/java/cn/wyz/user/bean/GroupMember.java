package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 群组成员信息
 *
 * @author zhouzhitong
 * @since 2024-03-20
 **/
@Data
@TableName(value = "group_member", resultMap = "GroupMemberMap")
public class GroupMember extends BaseEntity {

    /**
     * 群组ID
     */
    private Long groupId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private Integer roleType;

    /**
     * 群组成员状态
     */
    private Integer status;

    /**
     * 加入时间
     */
    private LocalDateTime joinedAt;

}
