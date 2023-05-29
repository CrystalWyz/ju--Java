package cn.wyz.murdermystery.bean;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    @ApiModelProperty("创建者id")
    private Long userId;

    @ApiModelProperty("聚主题")
    private String title;

    @ApiModelProperty("聚类型")
    private Integer type;

    @ApiModelProperty("参与用户")
    private List<Long> participant;

    @ApiModelProperty("规模")
    private Integer scale;

    @ApiModelProperty("小姐姐参与人数")
    private Integer girlParticipantNum;

    @ApiModelProperty("小哥哥参与人数")
    private Integer boyParticipantNum;

    @ApiModelProperty("预计开始时间")
    private LocalDateTime begin;

    @ApiModelProperty("预计结束时间")
    private LocalDateTime finish;

    @ApiModelProperty("聚描述信息")
    private String description;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("配置信息")
    private JSONObject config;
}
