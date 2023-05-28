package cn.wyz.murdermystery.service.impl;

import cn.wyz.murdermystery.bean.User;
import cn.wyz.murdermystery.bean.dto.UserDTO;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.mapper.UserMapper;
import cn.wyz.murdermystery.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wnx
 */
@Component
public class UserServiceImpl implements UserService {

    private final BeanConvert beanConvert;

    private final UserMapper userMapper;

    public UserServiceImpl(BeanConvert beanConvert, UserMapper userMapper) {
        this.beanConvert = beanConvert;
        this.userMapper = userMapper;
    }

    @Override
    public Long createUser(UserDTO userDTO) {

        User user = beanConvert.userDTOToUser(userDTO);

        // 填充必要信息
        user.setBlemish(0);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        Long userId = userMapper.create(user);
        return userId;
    }
}
