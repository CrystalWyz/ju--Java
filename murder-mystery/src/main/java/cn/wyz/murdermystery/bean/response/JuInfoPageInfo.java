package cn.wyz.murdermystery.bean.response;

import com.alibaba.fastjson.JSONObject;
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

    @ApiModelProperty("聚类型")
    private Integer type;

    @ApiModelProperty("当前参与人数")
    private Integer participantNum;

    @ApiModelProperty("小姐姐参与人数")
    private Integer girlParticipantNum;

    @ApiModelProperty("小哥哥参与人数")
    private Integer boyParticipantNum;

    @ApiModelProperty("规模")
    private Integer scale;

    @ApiModelProperty("预计开始时间")
    private LocalDateTime begin;

    @ApiModelProperty("预计结束时间")
    private LocalDateTime finish;

    @ApiModelProperty("创建者id")
    private Long userId;

    @ApiModelProperty("配置信息")
    private JSONObject config;
}
