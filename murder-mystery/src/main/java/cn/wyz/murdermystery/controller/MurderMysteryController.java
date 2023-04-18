package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.service.JuInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wnx
 */
@RestController
@RequestMapping("/api/v1/murderMystery")
public class MurderMysteryController {

    private final JuInfoService juInfoService;

    public MurderMysteryController(JuInfoService juInfoService) {
        this.juInfoService = juInfoService;
    }

    @PostMapping("/create")
    public ResponseResult<String> create(JuInfoDTO juInfoDTO) {
        Long id = juInfoService.create(juInfoDTO);
        return ResponseResult.success(String.valueOf(id));
    }
}
