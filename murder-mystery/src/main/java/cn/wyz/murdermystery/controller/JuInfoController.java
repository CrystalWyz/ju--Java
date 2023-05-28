package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.bean.response.Page;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/juInfo")
public class JuInfoController {

    @ApiModelProperty("聚分页查询")
    @PostMapping("/page")
    public ResponseResult<Page<JuInfoPageInfo>> detail(@RequestBody PageVM<JuInfoDTO> pageRequest) {
        return null;
    }

    @ApiModelProperty("查询聚详情")
    @GetMapping("/detail")
    public ResponseResult<JuInfoDTO> detail(@RequestParam Long juInfoId) {
        return null;
    }

    @ApiModelProperty("创建聚")
    @PostMapping("/create")
    public ResponseResult<String> createUser(@RequestBody JuInfoDTO juInfoDTO) {
        return null;
    }

    @ApiModelProperty("删除聚")
    @PostMapping("/delete/{juInfoId}")
    public ResponseResult<String> deleteUser(@PathVariable Long juInfoId) {
        return null;
    }
}
