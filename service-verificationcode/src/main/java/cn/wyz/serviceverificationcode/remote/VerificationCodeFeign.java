package cn.wyz.serviceverificationcode.remote;

import cn.wyz.common.bean.ResponseResult;
import cn.wyz.common.bean.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangnanxiang
 */
@FeignClient("service-verificationcode")
public interface VerificationCodeFeign {

    /**
     * 获取六位数验证码
     * @return 验证码
     */
    @GetMapping(value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size")int size);
}
