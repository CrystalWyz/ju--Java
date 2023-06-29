package cn.wyz.murdermystery.bean.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangnanxiang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "JuInfoDTO", description = "")
public class JuInfoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Schema(name = "id")
    private Long id;

    @Schema(name = "创建者id")
    private Long userId;

    @Schema(name = "聚主题")
    private String title;

    @Schema(name = "聚类型")
    private Integer type;

    @Schema(name = "参与用户")
    private List<Long> participant;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "小姐姐参与人数")
    private Integer girlParticipantNum;

    @Schema(name = "小哥哥参与人数")
    private Integer boyParticipantNum;

    @Schema(name = "预计开始时间")
    private LocalDateTime begin;

    @Schema(name = "预计结束时间")
    private LocalDateTime finish;

    @Schema(name = "聚描述信息")
    private String description;

    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    @Schema(name = "更新时间")
    private LocalDateTime updateTime;

    @Schema(name = "配置信息")
    private JSONObject config;
}
