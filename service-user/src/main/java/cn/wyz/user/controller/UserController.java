package cn.wyz.user.controller;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.common.bean.request.PageVM;
import cn.wyz.user.bean.User;
import cn.wyz.common.bean.dto.UserDTO;
import cn.wyz.user.bean.response.UserPageInfo;
import cn.wyz.user.convert.UserBeanConvert;
import cn.wyz.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Tag(name = "UserController", description = "用户接口")
@RestController
@RequestMapping("/api/v1/murderMystery/user")
public class UserController {

    private final UserBeanConvert userBeanConvert;

    private final UserService userService;

    public UserController(UserBeanConvert userBeanConvert, UserService userService) {
        this.userBeanConvert = userBeanConvert;
        this.userService = userService;
    }

    @Operation(description = "用户分页查询")
    @PostMapping("/page")
    public ResponseResult<PageInfo<UserPageInfo>> userPage(@RequestBody PageVM<UserDTO> pageRequest) {
        List<UserPageInfo> users = userService.userPage(pageRequest);
        return ResponseResult.success(new PageInfo<>(users));
    }

    @Operation(description = "查询用户详情")
    @GetMapping("/detail")
    public ResponseResult<UserDTO> userDetail(@RequestParam Long userId) {
        User user = userService.userDetail(userId);
        return ResponseResult.success(userBeanConvert.userToUserDTO(user));
    }

    @Operation(description = "查询用户详情")
    @GetMapping("/detailByPhone")
    public ResponseResult<UserDTO> userDetailByPhone(@RequestParam String userPhone) {
        User user = userService.userDetail(userPhone);
        return ResponseResult.success(userBeanConvert.userToUserDTO(user));
    }

    @Operation(description = "创建用户")
    @PostMapping("/create")
    public ResponseResult<String> createUser(@RequestBody UserDTO userDTO) {
        Long userId = userService.createUser(userDTO);
        return ResponseResult.success(String.valueOf(userId));
    }

    @Operation(description = "删除用户")
    @PostMapping("/delete/{userId}")
    public ResponseResult<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseResult.success();
    }
}
