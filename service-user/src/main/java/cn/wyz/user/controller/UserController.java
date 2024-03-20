package cn.wyz.user.controller;

import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.User;
import cn.wyz.user.dto.UserDTO;
import cn.wyz.user.req.UserQuery;
import cn.wyz.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Tag(name = "UserController", description = "用户接口")
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController<User, UserDTO, UserQuery, UserService> {


}
