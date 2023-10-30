package cn.wyz.user.service.impl;

import cn.wyz.common.util.EncryptUtils;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.response.UserDTO;
import cn.wyz.user.mapper.UserMapper;
import cn.wyz.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wnx
 */
@Component
@Slf4j
public class UserServiceImpl
        extends MapperServiceImpl<UserMapper, User, UserDTO>
        implements UserService {

    @Override
    public UserDTO queryByUsername(String username) {
        LOGGER.trace("queryByUsername: {}", username);
        QueryWrapper<User> wrapper = buildQuery(null);
        wrapper.eq("username", username);
        User user = this.getOne(wrapper);
        return toDTO(user);
    }

    @Override
    public void copyProperties(UserDTO dto, User entity) {
        super.copyProperties(dto, entity);
        if (dto.getPassword() != null) {
            String encrypt = EncryptUtils.encrypt16(dto.getPassword());
            entity.setPassword(encrypt);
        }
    }

    @Override
    public UserDTO newDTO() {
        return new UserDTO();
    }

    @Override
    public User newEntity() {
        return new User();
    }
}
