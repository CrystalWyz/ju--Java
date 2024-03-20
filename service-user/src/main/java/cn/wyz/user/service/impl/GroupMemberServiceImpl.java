package cn.wyz.user.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.GroupMember;
import cn.wyz.user.dto.GroupMemberDTO;
import cn.wyz.user.mapper.GroupMemberMapper;
import cn.wyz.user.service.GroupMemberService;
import org.springframework.stereotype.Service;

/**
 * (GroupMember)表服务实现类
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Service("groupMemberService")
public class GroupMemberServiceImpl
        extends MapperServiceImpl<GroupMemberMapper, GroupMember, GroupMemberDTO>
        implements GroupMemberService {

}

