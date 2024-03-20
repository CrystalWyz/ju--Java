package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.dto.UserDTO;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface UserService extends MapperService<User, UserDTO> {

    UserDTO queryByUsername(String username);

    /**
     * 根据手机号返回用户信息
     *
     * @param phone 手机号
     * @return 用户信息，没有则返回null
     */
    UserDTO getByPhone(String phone);

    /**
     * 根据注册手机号
     *
     * @param phone 手机号
     * @return 用户信息
     */
    UserDTO register(String phone);

    /**
     * 根据id 获取用户信息
     *
     * @param ids
     * @return
     */
    Map<Long, User> getAllByIds(Collection<Long> ids);


}
