package cn.wyz.user.service;


import cn.wyz.common.bean.request.PageVM;
import cn.wyz.user.bean.User;
import cn.wyz.common.bean.dto.UserDTO;
import cn.wyz.user.bean.response.UserPageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface UserService {

    /**
     * 创建用户
     * @param userDTO 新用户信息
     * @return 用户id
     */
    Long createUser(UserDTO userDTO);

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 用户详情
     */
    User userDetail(Long userId);

    /**
     * 获取用户信息
     * @param phone 用户手机号
     * @return 用户详情
     */
    User userDetail(String phone);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void deleteUser(Long userId);

    /**
     * 用户分页查询
     * @param pageRequest 分页请求
     */
    List<UserPageInfo> userPage(PageVM<UserDTO> pageRequest);
}
