package cn.wyz.user.converter;

import cn.wyz.user.dto.UserTokenDTO;
import cn.wyz.user.vo.UserTokenVO;
import org.mapstruct.Mapper;

/**
 * @author wangnanxiang
 */
@Mapper(componentModel = "spring")
public interface JuUserBeanConvert {

    UserTokenVO UserTokenDTOToUserTokenVO(UserTokenDTO UserTokenDTO);
}
