package cn.wyz.murdermystery.bean.response;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name =  "JuInfoPageInfo", description = "聚分页信息")
public class JuInfoPageInfo {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "聚主题")
    private String title;

    @Schema(name = "聚类型")
    private Integer type;

    @Schema(name = "当前参与人数")
    private Integer participantNum;

    @Schema(name = "小姐姐参与人数")
    private Integer girlParticipantNum;

    @Schema(name = "小哥哥参与人数")
    private Integer boyParticipantNum;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "预计开始时间")
    private LocalDateTime begin;

    @Schema(name = "预计结束时间")
    private LocalDateTime finish;

    @Schema(name = "创建者id")
    private Long userId;

    @Schema(name = "配置信息")
    private JSONObject config;
}
