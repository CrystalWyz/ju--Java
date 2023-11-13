package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.mapper.typeHandler.JsonTypeHandler;
import cn.wyz.mapper.typeHandler.ListTypeHandler;
import cn.wyz.murdermystery.bean.constance.JuInfoConfigConstance;
import cn.wyz.murdermystery.type.GameStatus;
import cn.wyz.user.bean.User;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@Deprecated
@TableName(value = "ju_info", autoResultMap = true)
public class JuInfo extends BaseEntity {

    /**
     * 活动发起人
     *
     * @see User#getId()
     */
    private Long userId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动类型
     */
    private Integer type;

    /**
     * 活动参与人
     *
     * @see User#getId()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Long> participant;

    /**
     * 活动规模
     */
    private Integer scale;

    /**
     * 活动状态
     *
     * @see GameStatus
     */
    private GameStatus status;

    /**
     * 女生参加人数
     */
    private Integer girlParticipantNum;

    /**
     * 男生参加人数
     */
    private Integer boyParticipantNum;

    /**
     * 活动开始时间
     */
    private LocalDateTime begin;

    /**
     * 活动结束时间
     */
    private LocalDateTime finish;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动配置
     *
     * @see JuInfoConfigConstance 所有关键key
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private JSONObject config;

}
