package cn.wyz.serviceverificationcode.remote;

import cn.wyz.common.bean.request.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wnx
 */
@FeignClient("service-user")
public interface UserFeign {

    @Operation(description = "查询用户详情")
    @GetMapping("/detailByPhone")
    ResponseResult<Object> userDetailByPhone(@RequestParam String userPhone);
}
