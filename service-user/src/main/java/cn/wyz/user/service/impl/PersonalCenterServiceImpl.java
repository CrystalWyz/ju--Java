package cn.wyz.user.service.impl;

import cn.hutool.core.lang.Pair;
import cn.wyz.user.dto.UserDTO;
import cn.wyz.user.dto.UserDetailDTO;
import cn.wyz.user.handler.UserDetailHandler;
import cn.wyz.user.service.PersonalCenterService;
import cn.wyz.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人中心 接口
 *
 * @author zhouzhitong
 * @since 2024-03-16
 **/
@Service
@Slf4j
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private List<UserDetailHandler> userDetailServices;

    @Override
    public UserDetailDTO getUserDetail(Long userId) {
        LOGGER.trace("get user detail: {}", userId);
        UserDTO user = userService.get(userId);
        UserDetailDTO userDetail = new UserDetailDTO();
        userDetail.setUser(user);
        if (userDetailServices != null) {
            for (UserDetailHandler userDetailHandler : userDetailServices) {
                Pair<String, Object> pair = userDetailHandler.getUserDetail(userId);
                userDetail.addUserDetail(pair.getKey(), pair.getValue());
            }
        }
        return userDetail;
    }

}
