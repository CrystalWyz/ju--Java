package cn.wyz.user.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@Schema(name = "User对象", description = "")
public class User {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "用户名")
    private String name;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "性别")
    private Integer gender;

    @Schema(name = "身份")
    private Integer identity;

    @Schema(name = "污点")
    private Integer blemish;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "区号")
    private String phoneArea;

    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    @Schema(name = "修改时间")
    private LocalDateTime updateTime;
}
