package cn.wyz.user.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyz
 */
@Data
@Schema(name = "OneClickLoginDTO", description = "一键登录 DTO")
public class OneClickLoginDTO {

    @NotNull(message = "手机号不可以为空")
    @Schema(name = "手机号")
    private String phone;

    @NotNull(message = "校验码不可以为空")
    @Schema(name = "校验码")
    private String verifyCode;
}
