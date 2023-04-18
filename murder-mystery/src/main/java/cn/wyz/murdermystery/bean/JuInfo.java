package cn.wyz.murdermystery.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value = "JuInfo对象", description = "")
public class JuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("聚主题")
    private String title;

    @ApiModelProperty("参与用户")
    private Object users;

    @ApiModelProperty("	预计开始时间")
    private LocalDateTime begin;

    @ApiModelProperty("预计结束时间")
    private LocalDateTime finish;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("聚描述信息")
    private String description;

    @ApiModelProperty("创建者id")
    private Long userId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
