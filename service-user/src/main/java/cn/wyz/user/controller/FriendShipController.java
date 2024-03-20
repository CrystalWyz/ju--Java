package cn.wyz.user.controller;


import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.FriendShip;
import cn.wyz.user.dto.FriendShipDTO;
import cn.wyz.user.req.FriendShipQuery;
import cn.wyz.user.service.FriendShipService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (FriendShip)表控制层
 *
 * @author zhouzhitong
 * @since 2024-03-25 22:46:23
 */
@RestController
@RequestMapping("/api/v1/friendShips")
public class FriendShipController
        extends BaseController<FriendShip, FriendShipDTO, FriendShipQuery, FriendShipService> {


}
