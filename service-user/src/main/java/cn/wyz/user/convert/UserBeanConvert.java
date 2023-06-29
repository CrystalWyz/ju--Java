package cn.wyz.user.convert;

import cn.wyz.user.bean.User;
import cn.wyz.common.bean.dto.UserDTO;
import cn.wyz.user.bean.response.UserPageInfo;
import org.mapstruct.Mapper;

/**
 * @author wnx
 */
@Mapper(componentModel = "spring")
public interface UserBeanConvert {

    /**
     * 用户传输对象转用户pojo对象
     * @param userDTO
     * @return
     */
    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);

    UserPageInfo userTOUserPageInfo(User user);
}
