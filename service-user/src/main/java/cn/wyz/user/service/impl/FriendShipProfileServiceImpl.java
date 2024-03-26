package cn.wyz.user.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.FriendShipProfile;
import cn.wyz.user.dto.FriendShipProfileDTO;
import cn.wyz.user.mapper.FriendShipProfileMapper;
import cn.wyz.user.service.FriendShipProfileService;
import org.springframework.stereotype.Service;

/**
 * (FriendShipProfile)表服务实现类
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:31
 */
@Service("friendShipProfileService")
public class FriendShipProfileServiceImpl
        extends MapperServiceImpl<FriendShipProfileMapper, FriendShipProfile, FriendShipProfileDTO>
        implements FriendShipProfileService {

}

