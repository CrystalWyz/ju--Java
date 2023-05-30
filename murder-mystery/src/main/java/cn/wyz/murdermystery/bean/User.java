package cn.wyz.murdermystery.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "User对象", description = "")
public class User {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("身份")
    private Integer identity;

    @ApiModelProperty("污点")
    private Integer blemish;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("区号")
    private String phoneArea;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
}
