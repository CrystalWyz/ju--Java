package cn.wyz.user.controller;


import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.Group;
import cn.wyz.user.dto.GroupDTO;
import cn.wyz.user.req.GroupQuery;
import cn.wyz.user.service.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (Group)表控制层
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:29
 */
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController
        extends BaseController<Group, GroupDTO, GroupQuery, GroupService> {


}
