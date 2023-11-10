package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.request.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyz
 */
@RestController
@RequestMapping("/api/v1/murderMysteryUser")
@Tag(name = "剧本杀用户信息接口", description = "剧本杀用户信息接口")
public class MurderMysteryUserController {

    @Operation(description = "获取剧本杀用户信息")
    @GetMapping("/detail")
    public ResponseResult<String> murderMysteryUserDetail(@RequestParam Long id) {
        return ResponseResult.success(null);
    }

    @Operation(description = "获取用户当前申请信息")
    @GetMapping("/apply")
    public ResponseResult<String> murderMysteryUserApply(@RequestParam Long id) {
        return ResponseResult.success(null);
    }

    @Operation(description = "获取用户申请历史")
    @GetMapping("/applyHistory")
    public ResponseResult<String> applyHistory(@RequestParam Long id) {
        return ResponseResult.success(null);
    }
}
