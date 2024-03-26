package cn.wyz.user.controller;


import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.mapper.controller.BaseController;
import cn.wyz.user.bean.FriendApplyNote;
import cn.wyz.user.dto.FriendApplyNoteDTO;
import cn.wyz.user.req.FriendApplyNoteQuery;
import cn.wyz.user.service.FriendApplyNoteService;
import org.springframework.web.bind.annotation.*;


/**
 * (FriendApplyNote)表控制层
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:31
 */
@RestController
@RequestMapping("/api/v1/friendApplyNotes")
public class FriendApplyNoteController
        extends BaseController<FriendApplyNote, FriendApplyNoteDTO, FriendApplyNoteQuery, FriendApplyNoteService> {

    @PutMapping("/pass/{id}")
    public ResponseResult<Boolean> pass(@PathVariable("id") Long id, @RequestBody FriendApplyNoteQuery query) {
        return ResponseResult.success(service().pass(id, query));
    }

    @PutMapping("/reject/{id}")
    public ResponseResult<Boolean> reject(@PathVariable("id") Long id, @RequestBody FriendApplyNoteQuery query) {
        return ResponseResult.success(service().reject(id, query));
    }

}
