package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.murdermystery.bean.constance.JuInfoConfigConstance;
import cn.wyz.murdermystery.type.JuInfoStatus;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangnanxiang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "JuInfoDTO", description = "剧本杀发布记录DTO")
public class JuInfoDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "创建者id")
    private Long userId;

    @Schema(name = "聚主题")
    private String title;

    @Schema(name = "聚类型")
    private Integer type;

    @Schema(name = "参与用户")
    private List<Long> participant;

    @Schema(name = "状态")
    private JuInfoStatus status;

    @Schema(name = "规模")
    private Integer scale;

    @Schema(name = "参与小姐姐参与人数")
    private Integer girlParticipantNum = 0;

    @Schema(name = "参与小哥哥参与人数")
    private Integer boyParticipantNum = 0;

    @Schema(name = "预计开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime begin;

    @Schema(name = "预计结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finish;

    @Schema(name = "聚描述信息")
    private String description;

    /**
     * @see JuInfoConfigConstance 所有关键key
     */
    @Schema(name = "配置信息")
    private JSONObject config;

    /**
     * 人数是否足够
     *
     * @return 是否足够
     */
    public boolean full() {
        return getBoyParticipantNum() + getGirlParticipantNum() == scale;
    }

    public List<Long> getParticipant() {
        if (participant == null) {
            participant = Lists.newArrayList();
        }
        return participant;
    }

    /**
     * 获取需要参加的男生数量
     *
     * @return 男生数量
     */
    public int getNeedBoyNum() {
        if (config == null) {
            return 0;
        }
        return config.getIntValue(JuInfoConfigConstance.BOY_PARTICIPANT_NUM);
    }

    /**
     * 获取需要参加的女生数量
     *
     * @return 女生数量
     */
    public int getNeedGirlNum() {
        if (config == null) {
            return 0;
        }
        return config.getIntValue(JuInfoConfigConstance.GIRL_PARTICIPANT_NUM);
    }

    /**
     * 是否需要请求加入
     *
     * @return 是否需要请求加入
     */
    public boolean isNeedApply() {
        if (config == null) {
            return false;
        }
        return config.getBooleanValue(JuInfoConfigConstance.ENABLE_APPLY);
    }

}
