package cn.wyz.murdermystery.controller;

import cn.wyz.bean.dto.VerificationCodeDTO;
import cn.wyz.bean.response.TokenResponseDTO;
import cn.wyz.insternalcommon.bean.ResponseResult;
import cn.wyz.service.VerificationCodeService;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangnanxiang
 */
@RestController
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;

    public VerificationCodeController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @GetMapping("/verification-code")
    public ResponseResult<?> verificationCode(@RequestParam String passengerPhone) {
        verificationCodeService.generatorVerificationCode(passengerPhone);
        return ResponseResult.success();
    }

    @PostMapping("/verification-code-check")
    public ResponseResult<TokenResponseDTO> checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        TokenResponseDTO token = verificationCodeService.checkVerificationCode(verificationCodeDTO.getPassengerPhone(),
                verificationCodeDTO.getVerificationCode());
         return ResponseResult.success(token);
    }
}
