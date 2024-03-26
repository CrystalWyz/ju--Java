package cn.wyz.user.service.impl;

import cn.wyz.common.util.EncryptUtils;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.User;
import cn.wyz.user.constant.Gender;
import cn.wyz.user.dto.UserDTO;
import cn.wyz.user.mapper.UserMapper;
import cn.wyz.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wnx
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl
        extends MapperServiceImpl<UserMapper, User, UserDTO>
        implements UserService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String USER_KEY = "user:";

    @Override
    public UserDTO queryByUsername(String username) {
        LOGGER.trace("queryByUsername: {}", username);
        QueryWrapper<User> wrapper = buildQuery(null);
        wrapper.eq("username", username);
        User user = this.getOne(wrapper);
        return toDTO(user);
    }

    @Override
    public UserDTO get(Long id) {
        LOGGER.trace("get: {}", id);
        User user = (User) redisTemplate.opsForValue().get(USER_KEY + id);
        if (ObjectUtils.isEmpty(user)) {
            user = this.getById(id);
            if (ObjectUtils.isEmpty(user)) {
                return null;
            }
            LOGGER.debug("cache user: {}", user);
            redisTemplate.opsForValue().set(USER_KEY + id, user, 1, TimeUnit.DAYS);
        }
        return toDTO(user);
    }

    @Override
    public Map<Long, User> getAllByIds(Collection<Long> ids) {
        LOGGER.trace("getAllByIds: {}", ids);

        Map<Long, User> map = new HashMap<>();
        ids.forEach(id -> {
            User user = (User) redisTemplate.opsForValue().get(USER_KEY + id);
            if (ObjectUtils.isEmpty(user)) {
                user = getById(id);
                redisTemplate.opsForValue().set(USER_KEY + id, user, 1, TimeUnit.DAYS);
            }
            map.put(id, user);
        });

        return map;
    }

    @Override
    public UserDTO getByPhone(String phone) {
        LOGGER.trace("getByPhone: {}", phone);
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
        String nickName = "刁民-" + RandomUtils.nextLong(0, Long.MAX_VALUE);
        user.setNickName(nickName);
        user.setUsername(phone);
        user.setGender(Gender.UNKNOWN);
        return this.add(user);
    }

    @Override
    public User copyProperties(UserDTO dto, User entity) {
        super.copyProperties(dto, entity);
        if (dto.getPassword() != null) {
            String encrypt = EncryptUtils.encrypt16(dto.getPassword());
            entity.setPassword(encrypt);
        }
        return entity;
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
