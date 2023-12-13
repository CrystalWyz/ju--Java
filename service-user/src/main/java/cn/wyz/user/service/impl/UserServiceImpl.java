package cn.wyz.user.service.impl;

import cn.wyz.common.util.EncryptUtils;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.converter.JuUserBeanConvert;
import cn.wyz.user.mapper.UserMapper;
import cn.wyz.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wnx
 */
@Service
@Slf4j
public class UserServiceImpl
        extends MapperServiceImpl<UserMapper, User, UserDTO>
        implements UserService {

    private final JuUserBeanConvert convert;

    public UserServiceImpl(JuUserBeanConvert convert) {
        this.convert = convert;
    }

    @Override
    public UserDTO queryByUsername(String username) {
        LOGGER.trace("queryByUsername: {}", username);
        QueryWrapper<User> wrapper = buildQuery(null);
        wrapper.eq("username", username);
        User user = this.getOne(wrapper);
        return toDTO(user);
    }

    @Override
    public UserDTO getByPhone(String phone) {

        QueryWrapper<User> wrapper = buildQuery(null);
        wrapper.eq("phone", phone);

        User user = this.getOne(wrapper);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return toDTO(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDTO register(String phone) {
        LOGGER.info("register: {}", phone);
        UserDTO user = new UserDTO();
        user.setPhone(phone);
        String name = "刁民-" + RandomUtils.nextLong(0, Long.MAX_VALUE);
        user.setNickName(name);
        user.setUsername(phone);
        return this.add(user);
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
