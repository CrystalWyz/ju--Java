package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.GroupMember;
import cn.wyz.user.dto.GroupMemberDTO;

/**
 * (GroupMember)表服务接口
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
public interface GroupMemberService
        extends MapperService<GroupMember, GroupMemberDTO> {

    @Override
    default GroupMemberDTO newDTO() {
        return new GroupMemberDTO();
    }

    @Override
    default GroupMember newEntity() {
        return new GroupMember();
    }

}

