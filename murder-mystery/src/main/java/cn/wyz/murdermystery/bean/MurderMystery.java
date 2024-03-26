package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.mapper.typeHandler.JsonTypeHandler;
import cn.wyz.mapper.typeHandler.ListTypeHandler;
import cn.wyz.murdermystery.bean.constance.MurderConfigConstance;
import cn.wyz.murdermystery.type.GameStatus;
import cn.wyz.user.bean.User;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 剧本杀记录
 *
 * @author wyz
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Description("剧本杀记录")
@TableName(value = "murder_mystery", autoResultMap = true)
public class MurderMystery extends BaseEntity {

    /**
     * 活动标题
     */
    private String title;

    /**
     * 游戏状态
     */
    private GameStatus status;

    /**
     * 活动预期开始时间
     */
    private LocalDateTime beginExpected;

    /**
     * 活动预期结束时间
     */
    private LocalDateTime finishExpected;

    /**
     * 活动实际开始时间
     */
    private LocalDateTime beginActual;

    /**
     * 活动实际结束时间
     */
    private LocalDateTime finishActual;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动规模
     */
    private Integer scale;

    /**
     * 需要女生参数人数
     */
    private Integer girlParticipantNum;

    /**
     * 需要男生参加人数
     */
    private Integer boyParticipantNum;

    /**
     * 活动配置
     *
     * @see MurderConfigConstance 所有关键key
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private JSONObject config;

    /**
     * 已经参加的女生的 userId
     *
     * @see User#getId() User.getId()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Long> girlParticipant;

    /**
     * 已经参加的男生的 userId
     *
     * @see User#getId() User.getId()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Long> boyParticipant;

    /**
     * 申请参加的 申请记录Id
     *
     * @see MurderMysteryApply#getId() User.getId()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Long> applyParticipant;

    /**
     * 已经签到的用户Id
     *
     * @see User#getId() User.getId()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Long> signInParticipant;

    /**
     * 活动地点
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<Integer> area;

    /**
     * 所在地址
     */
    private String address;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 标签
     *
     * @see Tag#getName()
     */
    @TableField(typeHandler = ListTypeHandler.class)
    private List<String> tags;

    /**
     * 活动图片地址
     */
    private String imagePath;

    /**
     * 距离 TODO
     */
    private Double dist;

    /**
     * 查看次数
     */
    private Long reviews;

    private String location;
}
