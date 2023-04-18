package cn.wyz.murdermystery.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangnanxiang
 */
@Data
@ApiModel(value = "JuInfoDTO", description = "")
public class JuInfoDTO {

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

    @ApiModelProperty("聚描述信息")
    private String description;

    @ApiModelProperty("创建者id")
    private Long userId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
