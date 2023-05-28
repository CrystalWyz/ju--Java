package cn.wyz.murdermystery.convert;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.User;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.dto.UserDTO;
import cn.wyz.murdermystery.bean.response.UserPageInfo;
import org.mapstruct.Mapper;

/**
 * @author wangnanxiang
 */
@Mapper(componentModel = "spring")
public interface BeanConvert {

    JuInfo juInfoDTOToJuInfo(JuInfoDTO juInfoDTO);

    /**
     * 用户传输对象转用户pojo对象
     * @param userDTO
     * @return
     */
    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);

    UserPageInfo userTOUserPageInfo(User user);
}
