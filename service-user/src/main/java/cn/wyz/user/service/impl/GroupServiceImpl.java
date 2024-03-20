package cn.wyz.user.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.Group;
import cn.wyz.user.dto.GroupDTO;
import cn.wyz.user.mapper.GroupMapper;
import cn.wyz.user.service.GroupService;
import org.springframework.stereotype.Service;

/**
 * (Group)表服务实现类
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@Service("groupService")
public class GroupServiceImpl
        extends MapperServiceImpl<GroupMapper, Group, GroupDTO>
        implements GroupService {

}

