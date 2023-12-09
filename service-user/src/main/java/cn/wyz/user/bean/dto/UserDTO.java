package cn.wyz.user.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.constant.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "JuInfoDTO", description = "用户 DTO")
public class UserDTO extends BaseDTO {

    @Schema(name = "用户名")
    private String name;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "性别")
    private Gender gender;

    @Schema(name = "身份")
    private Integer identity;

    @Schema(name = "账户名")
    private String username;

    @Schema(name = "登录密码")
    @Nullable
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
