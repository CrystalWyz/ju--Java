package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.FriendShip;
import cn.wyz.user.dto.FriendShipDTO;
import cn.wyz.user.req.FriendApplyNoteQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * (FriendShip)表服务接口
 *
 * @author zhouzhitong
 * @since 2024-03-25 22:46:23
 */
public interface FriendShipService
        extends MapperService<FriendShip, FriendShipDTO> {

    /**
     * 添加好友
     * <p>
     * TODO 待优化, 这个参数类型应该定义为 BO, 为内部调用. Query/Req 等参数应该是给前段调用的
     *
     * @param applyNote 申请信息
     */
    @Transactional(rollbackFor = Exception.class)
    void addFriend(FriendApplyNoteQuery applyNote);

    @Override
    default FriendShipDTO newDTO() {
        return new FriendShipDTO();
    }

    @Override
    default FriendShip newEntity() {
        return new FriendShip();
    }

}

