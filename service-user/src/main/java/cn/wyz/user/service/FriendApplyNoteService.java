package cn.wyz.user.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.user.bean.FriendApplyNote;
import cn.wyz.user.dto.FriendApplyNoteDTO;
import cn.wyz.user.req.FriendApplyNoteQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * (FriendApplyNote)表服务接口
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:31
 */
public interface FriendApplyNoteService
        extends MapperService<FriendApplyNote, FriendApplyNoteDTO> {

    @Transactional(rollbackFor = Exception.class)
    Boolean pass(Long id, FriendApplyNoteQuery query);

    @Transactional(rollbackFor = Exception.class)
    Boolean reject(Long id, FriendApplyNoteQuery query);

    @Override
    default FriendApplyNoteDTO newDTO() {
        return new FriendApplyNoteDTO();
    }

    @Override
    default FriendApplyNote newEntity() {
        return new FriendApplyNote();
    }

}

