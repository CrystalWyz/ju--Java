package cn.wyz.user.service.impl;

import cn.wyz.common.exception.ResourceException;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.FriendApplyNote;
import cn.wyz.user.dto.FriendApplyNoteDTO;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.mapper.FriendApplyNoteMapper;
import cn.wyz.user.req.FriendApplyNoteQuery;
import cn.wyz.user.service.FriendApplyNoteService;
import cn.wyz.user.service.FriendShipService;
import cn.wyz.user.type.ApplyStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (FriendApplyNote)表服务实现类
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:31
 */
@Slf4j
@Service("friendApplyNoteService")
@AllArgsConstructor
public class FriendApplyNoteServiceImpl
        extends MapperServiceImpl<FriendApplyNoteMapper, FriendApplyNote, FriendApplyNoteDTO>
        implements FriendApplyNoteService {

    private final FriendShipService friendShipService;

    @Override
    public Boolean pass(Long id, FriendApplyNoteQuery query) {
        LOGGER.info("pass friend apply id:{}", id);
        FriendApplyNoteDTO dto = get(id);
        if (dto == null) {
            return false;
        }
        if (dto.getStatus() == ApplyStatus.ACCEPTED) {
            return true;
        }
        if (dto.getStatus() == ApplyStatus.REJECTED) {
            throw new ResourceException("已拒绝, 不能再次操作");
        }
        Long userId = SecurityContextHolder.getContext().getUserId();
        if (dto.getTargetUserId().equals(userId)) {
            dto.setStatus(ApplyStatus.ACCEPTED);
        } else {
            throw new ResourceException("非法操作");
        }

        // 添加好友
        query.setPassNickName(dto.getPassNickName());
        query.setCreatedBy(dto.getCreatedBy());
        query.setTargetUserId(dto.getTargetUserId());
        friendShipService.addFriend(query);

        return update(dto) != null;
    }

    @Override
    public Boolean reject(Long id, FriendApplyNoteQuery query) {
        LOGGER.info("reject friend apply id:{}", id);
        FriendApplyNoteDTO dto = get(id);
        if (dto == null) {
            return false;
        }
        if (dto.getStatus() == ApplyStatus.REJECTED) {
            return true;
        }
        if (dto.getStatus() == ApplyStatus.ACCEPTED) {
            throw new ResourceException("已接受, 不能再次操作");
        }
        Long userId = SecurityContextHolder.getContext().getUserId();
        if (dto.getTargetUserId().equals(userId)) {
            dto.setStatus(ApplyStatus.REJECTED);
        } else {
            throw new ResourceException("非法操作");
        }
        return update(dto) != null;
    }

    @Override
    public FriendApplyNoteDTO add(FriendApplyNoteDTO dto) {
        dto.setStatus(ApplyStatus.APPLYING);
        return super.add(dto);
    }

}
