package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.Group;
import cn.wyz.user.dto.GroupDTO;

/**
 * (Group)表服务接口
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
public interface GroupService
        extends MapperService<Group, GroupDTO> {

    @Override
    default GroupDTO newDTO() {
        return new GroupDTO();
    }

    @Override
    default Group newEntity() {
        return new Group();
    }

}

