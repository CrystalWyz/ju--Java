package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.murdermystery.bean.dto.UserDTO;
import cn.wyz.murdermystery.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.Page;
import cn.wyz.murdermystery.bean.response.UserPageInfo;
import cn.wyz.murdermystery.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@ApiModel(value = "UserController", description = "用户接口")
@RestController
@RequestMapping("/api/v1/murderMystery/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiModelProperty("用户分页查询")
    @PostMapping("/page")
    public ResponseResult<Page<UserPageInfo>> detail(@RequestBody PageVM<UserDTO> pageRequest) {
        return null;
    }

    @ApiModelProperty("查询用户详情")
    @GetMapping("/detail")
    public ResponseResult<UserDTO> detail(@RequestParam Long userId) {
        return null;
    }

    @ApiModelProperty("创建用户")
    @PostMapping("/create")
    public ResponseResult<String> createUser(@RequestBody UserDTO userDTO) {
        Long userId = userService.createUser(userDTO);
        return ResponseResult.success(String.valueOf(userId));
    }

    @ApiModelProperty("删除用户")
    @PostMapping("/delete/{userId}")
    public ResponseResult<String> deleteUser(@PathVariable Long userId) {
        return null;
    }
}
