package cn.wyz.user.bean.response;

import cn.wyz.mapper.bean.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Data
public class UserDTO extends BaseDTO {

    @Schema(name = "用户名")
    private String name;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "性别")
    private Integer gender;

    @Schema(name = "身份")
    private Integer identity;

    @Schema(name = "账户名")
    private String username;

    @Schema(name = "登录密码")
    private String password;

    @Schema(name = "是否启用")
    private Boolean enabled;

    @Schema(name = "污点")
    private Integer blemish;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "区号")
    private String phoneArea;

    /**
     * 是否是超级管理员
     */
    @Schema(name = "是否是超级管理员")
    private Boolean isSupperAdmin = false;

}