package cn.wyz.user.service.impl;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.mapper.vo.PageResultVO;
import cn.wyz.user.bean.FriendShip;
import cn.wyz.user.dto.FriendShipDTO;
import cn.wyz.user.dto.UserDTO;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.mapper.FriendShipMapper;
import cn.wyz.user.req.FriendApplyNoteQuery;
import cn.wyz.user.service.FriendShipService;
import cn.wyz.user.service.UserService;
import cn.wyz.user.type.FriendType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (FriendShip)表服务实现类
 *
 * @author zhouzhitong
 * @since 2024-03-25 22:46:23
 */
@Service("friendShipService")
@Slf4j
@AllArgsConstructor
public class FriendShipServiceImpl
        extends MapperServiceImpl<FriendShipMapper, FriendShip, FriendShipDTO>
        implements FriendShipService {

    private final UserService userService;

    @Override
    public void addFriend(FriendApplyNoteQuery applyNote) {
        LOGGER.info("add friend:{}", applyNote);
        List<FriendShip> friendShips = new ArrayList<>();
        Long targetUsrId = applyNote.getTargetUserId();
        Long createdBy = applyNote.getCreatedBy();
        String tToCNickName = applyNote.getNickName();
        String cToTNickName = applyNote.getPassNickName();
        if (StringUtils.isNotBlank(tToCNickName)) {
            UserDTO targetUser = userService.get(createdBy);
            tToCNickName = targetUser.getNickName();
        }
        if (StringUtils.isNotBlank(cToTNickName)) {
            UserDTO createdUser = userService.get(createdBy);
            cToTNickName = createdUser.getNickName();
        }
        FriendShip targetFriendShip = new FriendShip();
        targetFriendShip.setUserIdFrom(targetUsrId);
        targetFriendShip.setUserIdTo(createdBy);
        targetFriendShip.setFriendType(FriendType.NORMAL);
        targetFriendShip.setNickName(tToCNickName);
        friendShips.add(targetFriendShip);

        new FriendShip();
        FriendShip createdByFriendShip = new FriendShip();
        createdByFriendShip.setUserIdFrom(createdBy);
        createdByFriendShip.setUserIdTo(targetUsrId);
        createdByFriendShip.setFriendType(FriendType.NORMAL);
        createdByFriendShip.setNickName(cToTNickName);
        friendShips.add(createdByFriendShip);
        boolean res = saveBatch(friendShips);
        LOGGER.info("add friend ship success. friendShips: {}. result: {}", friendShips, res);
    }

    @Override
    public <Query extends BaseRequest> PageResultVO<FriendShipDTO> page(Query query) {
        query.setExtraProperty("userIdFrom", SecurityContextHolder.getCurUserId());
        return super.page(query);
    }
}

