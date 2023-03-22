package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wnx
 */
@RestController
@RequestMapping("/api/v1/murderMystery")
public class MurderMysteryController {

    @PostMapping("/create")
    public ResponseResult<String> create() {
        return ResponseResult.success();
    }
}
