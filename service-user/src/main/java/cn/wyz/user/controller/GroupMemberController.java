package cn.wyz.user.controller;


import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.GroupMember;
import cn.wyz.user.dto.GroupMemberDTO;
import cn.wyz.user.req.GroupMemberQuery;
import cn.wyz.user.service.GroupMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (GroupMember)表控制层
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@RestController
@RequestMapping("/api/v1/groupMembers")
public class GroupMemberController
        extends BaseController<GroupMember, GroupMemberDTO, GroupMemberQuery, GroupMemberService> {


}
