package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.FriendShipProfile;
import cn.wyz.user.dto.FriendShipProfileDTO;

/**
 * (FriendShipProfile)表服务接口
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:30
 */
public interface FriendShipProfileService
        extends MapperService<FriendShipProfile, FriendShipProfileDTO> {

    @Override
    default FriendShipProfileDTO newDTO() {
        return new FriendShipProfileDTO();
    }

    @Override
    default FriendShipProfile newEntity() {
        return new FriendShipProfile();
    }

}

