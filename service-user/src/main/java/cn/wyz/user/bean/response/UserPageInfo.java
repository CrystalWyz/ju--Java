package cn.wyz.user.bean.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserPageInfo", description = "用户分页信息")
public class UserPageInfo {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "用户名")
    private String name;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "手机号")
    private String phone;
}
