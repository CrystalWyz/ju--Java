package cn.wyz.user.service.impl;

import cn.wyz.user.dto.LoginDTO;
import cn.wyz.user.service.AuthorityService;
import cn.wyz.user.service.UserService;
import cn.wyz.user.vo.UserRoleVO;
import cn.wyz.user.vo.UserTokenVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Service
@Slf4j
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final UserService userService;

    @Override
    public UserTokenVO login(LoginDTO param) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public UserRoleVO getCurrentUserInfo() {
        return null;
    }
}
