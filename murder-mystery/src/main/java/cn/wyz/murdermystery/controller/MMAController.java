package cn.wyz.murdermystery.controller;

import cn.wyz.common.anno.Idempotence;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouzhitong
 * @since 2023-12-09
 **/
@RestController

public class MMAController {

    @Autowired
    private MurderMysteryApplyService murderMysteryApplyService;

    /**
     * 撤销申请
     *
     * @param id 申请ID
     * @return ResponseResult<Void>
     */
    @Idempotence
    @PatchMapping("/cancelApply/{id}")
    public ResponseResult<Void> cancelApply(@PathVariable("id") Long id) {
        murderMysteryApplyService.cancelApply(id);
        return ResponseResult.success();
    }

}
