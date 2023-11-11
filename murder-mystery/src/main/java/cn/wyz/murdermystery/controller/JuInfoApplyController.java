package cn.wyz.murdermystery.controller;

import cn.wyz.mapper.controller.BaseController;
import cn.wyz.mapper.vo.PageResultVO;
import cn.wyz.murdermystery.bean.MurderMysteryApply;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.request.JuInfoApplyRequest;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 剧本杀申请记录查询
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/juInfoApplies")
public class JuInfoApplyController
        extends BaseController<MurderMysteryApply, MurderMysteryApplyDTO, JuInfoApplyRequest, MurderMysteryApplyService> {

    /**
     * 查询当前用户的申请
     *
     * @param page
     * @return
     */
    @GetMapping("/currentApplyPage")
    public PageResultVO<MurderMysteryApplyDTO> currentApplyPage(JuInfoApplyRequest page) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        page.setUserId(userId);
        return service().page(page);
    }

}
