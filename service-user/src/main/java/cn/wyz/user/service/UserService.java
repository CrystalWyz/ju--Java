package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.dto.UserDTO;

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

}
