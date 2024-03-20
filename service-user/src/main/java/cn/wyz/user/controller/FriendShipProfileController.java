package cn.wyz.user.controller;


import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.FriendShipProfile;
import cn.wyz.user.dto.FriendShipProfileDTO;
import cn.wyz.user.req.FriendShipProfileQuery;
import cn.wyz.user.service.FriendShipProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (FriendShipProfile)表控制层
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
@RestController
@RequestMapping("/api/v1/friendShipProfiles")
public class FriendShipProfileController
        extends BaseController<FriendShipProfile, FriendShipProfileDTO, FriendShipProfileQuery, FriendShipProfileService> {


}
