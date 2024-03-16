package cn.wyz.user.controller;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.user.bean.dto.UserDetailDTO;
import cn.wyz.user.service.PersonalCenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人中心服务
 *
 * @author zhouzhitong
 * @since 2024-03-16
 **/
@Controller
@ResponseBody
@RequestMapping("/api/v1/personalCenter")
@AllArgsConstructor
public class PersonalCenterController {

    private final PersonalCenterService personalCenterService;

    /**
     * 个人中心查询
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    @GetMapping("/{userId}")
    public ResponseResult<UserDetailDTO> getUserDetail(@PathVariable("userId") Long userId) {
        UserDetailDTO detail = personalCenterService.getUserDetail(userId);
        return ResponseResult.success(detail);
    }

}
