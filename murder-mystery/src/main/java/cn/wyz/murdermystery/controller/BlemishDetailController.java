package cn.wyz.murdermystery.controller;

import cn.wyz.mapper.controller.BaseController;
import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.request.BlemishDetailRequest;
import cn.wyz.murdermystery.service.BlemishDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/blemishDetails")
public class BlemishDetailController
        extends BaseController<BlemishDetail, BlemishDetailDTO, BlemishDetailRequest, BlemishDetailService> {

}
