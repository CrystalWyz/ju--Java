package cn.wyz.serviceverificationcode.controller;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.common.bean.response.NumberCodeResponse;
import cn.wyz.serviceverificationcode.service.NumberCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangnanxiang
 */
@RestController
public class NumberCodeController {

    private final NumberCodeService numberCodeService;

    public NumberCodeController(NumberCodeService numberCodeService) {
        this.numberCodeService = numberCodeService;
    }


    @GetMapping("/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> numberCode(@PathVariable("size") Integer size) {

        Integer numberCode = numberCodeService.generateNumberCode(size);

        NumberCodeResponse data = new NumberCodeResponse(numberCode);
        return ResponseResult.success(data);
    }
}
