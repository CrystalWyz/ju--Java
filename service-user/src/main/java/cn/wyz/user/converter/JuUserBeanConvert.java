package cn.wyz.user.converter;

import cn.wyz.user.bean.dto.UserTokenDTO;
import cn.wyz.user.bean.vo.UserTokenVO;
import org.mapstruct.Mapper;

/**
 * @author wangnanxiang
 */
@Mapper(componentModel = "spring")
public interface JuUserBeanConvert {

    UserTokenVO UserTokenDTOToUserTokenVO(UserTokenDTO UserTokenDTO);
}
