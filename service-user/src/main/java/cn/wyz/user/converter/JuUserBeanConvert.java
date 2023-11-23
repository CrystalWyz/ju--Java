package cn.wyz.user.converter;

import cn.wyz.user.bean.User;
import cn.wyz.user.bean.bo.OneClickLoginBO;
import cn.wyz.user.bean.bo.UserBO;
import cn.wyz.user.bean.bo.UserTokenBO;
import cn.wyz.user.bean.dto.OneClickLoginDTO;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.bean.vo.UserTokenVO;
import org.mapstruct.Mapper;

/**
 * @author wangnanxiang
 */
@Mapper(componentModel = "spring")
public interface JuUserBeanConvert {
    OneClickLoginBO oneClickLoginDTOToOneClickLoginBO(OneClickLoginDTO oneClickLoginDTO);

    UserTokenVO userTokenBOToUserTokenVO(UserTokenBO userTokenBO);

    UserBO userTOUserBO(User user);

    UserBO userDTOToUserBO(UserDTO user);
}