package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.service.JuInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/juInfo")
@Tag(name = "聚接口样例", description = "聚接口样例")
public class JuInfoController {

    private final BeanConvert beanConvert;

    private final JuInfoService juInfoService;

    public JuInfoController(BeanConvert beanConvert, JuInfoService juInfoService) {
        this.beanConvert = beanConvert;
        this.juInfoService = juInfoService;
    }

    @Operation(description = "聚分页查询")
    @PostMapping("/page")
    public ResponseResult<PageInfo<JuInfoPageInfo>> juInfoPage(@RequestBody PageVM<JuInfoDTO> pageRequest) {
        List<JuInfoPageInfo> juInfoPageInfos = juInfoService.juInfoPage(pageRequest);
        return ResponseResult.success(new PageInfo<>(juInfoPageInfos));
    }

    @Operation(description = "查询聚详情")
    @GetMapping("/detail")
    public ResponseResult<JuInfoDTO> detail(@RequestParam Long juInfoId) {
        JuInfo juInfo = juInfoService.juInfoDetail(juInfoId);
        return ResponseResult.success(beanConvert.juInfoToJuInfoDTO(juInfo));
    }

    @Operation(description = "创建聚")
    @PostMapping("/create")
    public ResponseResult<String> createJuInfo(@RequestBody JuInfoDTO juInfoDTO) {
        Long id = juInfoService.createJuInfo(beanConvert.juInfoDTOToJuInfo(juInfoDTO));
        return ResponseResult.success(String.valueOf(id));
    }

    @Operation(description = "删除聚")
    @PostMapping("/delete/{juInfoId}")
    public ResponseResult<String> deleteJuInfo(@PathVariable Long juInfoId) {
        juInfoService.deleteJuInfo(juInfoId);
        return ResponseResult.success();
    }
}
