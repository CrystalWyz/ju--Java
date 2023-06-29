package cn.wyz.user.service.impl;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.user.bean.User;
import cn.wyz.common.bean.dto.UserDTO;
import cn.wyz.user.bean.response.UserPageInfo;
import cn.wyz.user.convert.UserBeanConvert;
import cn.wyz.user.mapper.UserMapper;
import cn.wyz.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wnx
 */
@Component
public class UserServiceImpl implements UserService {

    private final UserBeanConvert userBeanConvert;

    private final UserMapper userMapper;

    public UserServiceImpl(UserBeanConvert userBeanConvert, UserMapper userMapper) {
        this.userBeanConvert = userBeanConvert;
        this.userMapper = userMapper;
    }

    @Override
    public Long createUser(UserDTO userDTO) {

        User user = userBeanConvert.userDTOToUser(userDTO);

        // 填充必要信息
        user.setBlemish(0);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.save(user);

        return user.getId();
    }

    @Override
    public User userDetail(Long userId) {
        return userMapper.detail(userId);
    }

    @Override
    public User userDetail(String phone) {
        return userMapper.detailByPhone(phone);
    }

    @Override
    public void deleteUser(Long userId) {
        userMapper.delete(userId);
    }

    @Override
    public List<UserPageInfo> userPage(PageVM<UserDTO> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        User condition = userBeanConvert.userDTOToUser(pageRequest.getCondition());
        List<User> userList = userMapper.list(condition);
        if (ObjectUtils.isNotEmpty(userList)) {
            return userList.stream().map(userBeanConvert::userTOUserPageInfo).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
