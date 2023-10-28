package cn.wyz.user.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.response.UserDTO;
import cn.wyz.user.mapper.UserMapper;
import cn.wyz.user.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author wnx
 */
@Component
public class UserServiceImpl
        extends MapperServiceImpl<UserMapper, User, UserDTO>
        implements UserService {

    @Override
    public UserDTO newDTO() {
        return new UserDTO();
    }

    @Override
    public User newEntity() {
        return new User();
    }
}
