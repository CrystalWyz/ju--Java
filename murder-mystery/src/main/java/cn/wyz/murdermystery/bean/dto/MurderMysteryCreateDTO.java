package cn.wyz.murdermystery.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MurderMysteryCreateDTO", description = "聚--剧本杀dto")
public class MurderMysteryCreateDTO {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "标题")
    private String title;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "预计开始时间")
    private LocalDateTime beginExpected;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "预计结束时间")
    private LocalDateTime finishExpected;

    @Schema(name = "聚描述信息")
    private String description;

    @Schema(name = "创建者id")
    private Long userId;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "女生参与人数")
    private Integer girlParticipantNum;

    @Schema(name = "男生参与人数")
    private Integer boyParticipantNum;

    @Schema(name = "配置信息")
    private JSONObject config;

    @Schema(name = "女生参与列表")
    private List<Long> girlParticipant;

    @Schema(name = "男生参与列表")
    private List<Long> boyParticipant;

    @Schema(name = "所属地区")
    private List<Integer> area;

    @Schema(name = "详细地址")
    private String address;

    @Schema(name = "店铺名")
    private String shopName;
}
