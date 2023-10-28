package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.response.BlemishDetailPageInfo;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.service.BlemishDetailService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/blemishDetail")
public class BlemishDetailController {

    private final BeanConvert beanConvert;

    private final BlemishDetailService blemishDetailService;

    public BlemishDetailController(BeanConvert beanConvert, BlemishDetailService blemishDetailService) {
        this.beanConvert = beanConvert;
        this.blemishDetailService = blemishDetailService;
    }

    @Operation(description = "污点分页查询")
    @PostMapping("/page")
    public ResponseResult<PageInfo<BlemishDetailPageInfo>> blemishDetailPage(@RequestBody PageVM<BlemishDetailDTO> pageRequest) {
        List<BlemishDetailPageInfo> blemishDetailPageInfos = blemishDetailService.blemishDetailPage(pageRequest);
        return ResponseResult.success(new PageInfo<>(blemishDetailPageInfos));
    }

    @Operation(description = "查询污点详情")
    @GetMapping("/detail")
    public ResponseResult<BlemishDetailDTO> detail(@RequestParam Long blemishDetailId) {
        BlemishDetail blemishDetail = blemishDetailService.blemishDetailDetail(blemishDetailId);
        return ResponseResult.success(beanConvert.blemishDetailToBlemishDetailDTO(blemishDetail));
    }

    @Operation(description = "创建污点")
    @PostMapping("/create")
    public ResponseResult<String> createBlemishDetail(@RequestBody BlemishDetailDTO blemishDetailDTO) {
        Long id = blemishDetailService.createBlemishDetail(beanConvert.blemishDetailDTOToBlemishDetail(blemishDetailDTO));
        return ResponseResult.success(String.valueOf(id));
    }

    @Operation(description = "删除污点")
    @PostMapping("/delete/{blemishDetailId}")
    public ResponseResult<String> deleteBlemishDetail(@PathVariable Long blemishDetailId) {
        blemishDetailService.deleteBlemishDetail(blemishDetailId);
        return ResponseResult.success();
    }
}
