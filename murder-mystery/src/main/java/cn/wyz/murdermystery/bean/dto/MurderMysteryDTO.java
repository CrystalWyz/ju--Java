package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.murdermystery.bean.constance.MurderConfigConstance;
import cn.wyz.murdermystery.type.GameStatus;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangnanxiang
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MurderMysteryDTO", description = "聚--剧本杀dto")
public class MurderMysteryDTO extends BaseDTO {

    @Schema(name = "标题")
    private String title;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "预计开始时间")
    private LocalDateTime beginExpected;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "预计结束时间")
    private LocalDateTime finishExpected;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "实际开始时间")
    private LocalDateTime beginActual;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "实际结束时间")
    private LocalDateTime finishActual;

    @Schema(name = "聚描述信息")
    private String description;

    @Schema(name = "创建者id")
    private Long userId;

    /**
     * 游戏状态
     */
    @Schema(name = "游戏状态")
    private GameStatus status;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "需要女生参与人数")
    private Integer girlParticipantNum;

    @Schema(name = "需要男生参与人数")
    private Integer boyParticipantNum;

    /**
     * @see MurderConfigConstance MurderConfigConstance
     */
    @Schema(name = "配置信息")
    private JSONObject config;

    @Schema(name = "女生参与列表")
    private List<Long> girlParticipant = Lists.newLinkedList();

    @Schema(name = "男生参与列表")
    private List<Long> boyParticipant = Lists.newLinkedList();

    @Schema(name = "申请参与列表")
    private List<Long> applyParticipant = Lists.newLinkedList();

    @Schema(name = "签到列表")
    private List<Long> signInParticipant = Lists.newLinkedList();


    @Schema(name = "所属地区")
    private List<Integer> area;

    @Schema(name = "详细地址")
    private String address;

    @Schema(name = "店铺名")
    private String shopName;

    public boolean needApply() {
        if (config == null) {
            return false;
        }
        return config.getBooleanValue(MurderConfigConstance.ENABLE_APPLY);
    }

}
