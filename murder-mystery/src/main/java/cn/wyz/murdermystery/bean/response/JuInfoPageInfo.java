package cn.wyz.murdermystery.bean.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "JuInfoPageInfo", description = "聚分页信息")
public class JuInfoPageInfo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("聚主题")
    private String title;

    @ApiModelProperty("预计开始时间")
    private LocalDateTime begin;

    @ApiModelProperty("预计结束时间")
    private LocalDateTime finish;

    @ApiModelProperty("创建者id")
    private Long userId;
}
