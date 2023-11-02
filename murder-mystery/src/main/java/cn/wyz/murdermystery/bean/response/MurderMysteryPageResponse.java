package cn.wyz.murdermystery.bean.response;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "剧本杀分页实体", description = "剧本杀分页实体")
public class MurderMysteryPageResponse {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Schema(name = "id")
    private Long id;

    @Schema(name = "标题")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "开始时间")
    private LocalDateTime begin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "结束时间")
    private LocalDateTime finish;

    @Schema(name = "描述")
    private String description;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Schema(name = "创建人id")
    private Long userId;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "女生人数")
    private Integer girlParticipantNum;

    @Schema(name = "男生人数")
    private Integer boyParticipantNum;

    @Schema(name = "配置信息")
    private JSONObject config;

    @Schema(name = "女生参与人数")
    private Integer girlParticipant;

    @Schema(name = "男生参与人人数")
    private Integer boyParticipant;

    private String shopName;
}
