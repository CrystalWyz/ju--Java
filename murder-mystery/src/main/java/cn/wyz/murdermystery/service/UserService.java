package cn.wyz.murdermystery.service;

import cn.wyz.murdermystery.bean.dto.UserDTO;

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
}
