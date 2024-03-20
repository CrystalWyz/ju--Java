package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.murdermystery.bean.Tag;
import cn.wyz.murdermystery.bean.constance.MurderConfigConstance;
import cn.wyz.murdermystery.type.GameStatus;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author wangnanxiang
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(name = "MurderMysteryDTO", description = "聚--剧本杀dto")
public class MurderMysteryDTO extends BaseDTO {

    @Schema(name = "title", description = "标题")
    private String title;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "beginExpected", description = "预计开始时间")
    private LocalDateTime beginExpected;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "finishExpected", description = "预计结束时间")
    private LocalDateTime finishExpected;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "beginActual", description = "实际开始时间")
    private LocalDateTime beginActual;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Schema(name = "finishActual", description = "实际结束时间")
    private LocalDateTime finishActual;

    @Schema(name = "description", description = "聚描述信息")
    private String description;

    /**
     * 游戏状态
     */
    @Schema(name = "status", description = "游戏状态")
    private GameStatus status;

    @Schema(name = "scale", description = "规模")
    private Integer scale;

    @Schema(name = "girlParticipantNum", description = "需要女生参与人数")
    private Integer girlParticipantNum;

    @Schema(name = "boyParticipantNum", description = "需要男生参与人数")
    private Integer boyParticipantNum;

    /**
     * @see MurderConfigConstance MurderConfigConstance
     */
    @Schema(name = "config", description = "配置信息")
    private JSONObject config;

    @Schema(name = "girlParticipant", description = "女生参与列表")
    private List<Long> girlParticipant = Lists.newArrayList();

    @Schema(name = "boyParticipant", description = "男生参与列表")
    private List<Long> boyParticipant = Lists.newArrayList();

    @Schema(name = "applyParticipant", description = "申请参与列表")
    private List<Long> applyParticipant = Lists.newArrayList();

    @Schema(name = "signInParticipant", description = "签到列表")
    private List<Long> signInParticipant = Lists.newArrayList();

    @Schema(name = "area", description = "所属地区")
    private List<Integer> area;

    @Schema(name = "address", description = "详细地址")
    private String address;

    @Schema(name = "shopName", description = "店铺名")
    private String shopName;

    /**
     * @see Tag#getName() 标签名称
     */
    @Schema(name = "tags", description = "标签")
    private List<String> tags = Lists.newArrayList();

    @Schema(name = "boyParticipants", description = "男生参与者信息")
    private List<MurderMysteryUserDTO> boyParticipants;

    @Schema(name = "girlParticipants", description = "女生参与者信息")
    private List<MurderMysteryUserDTO> girlParticipants;

    @Schema(name = "createUsername", description = "创建人")
    private String createUsername;

    /**
     * 活动图片地址
     */
    @Schema(name = "imagePath", description = "活动图片地址")
    private String imagePath;

    /**
     * 距离 TODO
     */
    @Schema(name = "dist", description = "距离")
    private Double dist;

    /**
     * 查看次数
     */
    @Schema(name = "views", description = "查看次数")
    private Long reviews;

    /**
     * 获取所有参与者
     *
     * @return 所有参与者
     */
    public List<Long> getAllParticipant() {
        List<Long> allParticipant = Lists.newLinkedList();
        allParticipant.addAll(this.getGirlParticipant());
        allParticipant.addAll(this.getBoyParticipant());
        return allParticipant;
    }

    public boolean needApply() {
        if (config == null) {
            return false;
        }
        return config.getBooleanValue(MurderConfigConstance.ENABLE_APPLY);
    }

    public boolean isFull() {
        return Objects.equals(this.getBoyParticipant().size(), this.getBoyParticipantNum()) && Objects.equals(this.getGirlParticipant().size(), this.getGirlParticipantNum());
    }

    public boolean isGirlFull() {
        return Objects.equals(this.getGirlParticipant().size(), this.getGirlParticipantNum());
    }

    public boolean isBoyFull() {
        return Objects.equals(this.getBoyParticipant().size(), this.getBoyParticipantNum());
    }


    public int getBoyNum() {
        return getBoyParticipant().size();
    }

    public int getGirlNum() {
        return getGirlParticipant().size();
    }

}
