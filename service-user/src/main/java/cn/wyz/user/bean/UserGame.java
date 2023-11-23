package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户游戏属性
 *
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_game")
public class UserGame extends BaseEntity {

    /**
     * 用户ID
     *
     * @see User#getId()
     */
    private Long userId;

    /**
     * 剧本杀游戏等级
     */
    private Integer juLevel;

    /**
     * 剧本杀游戏次数
     */
    private Integer juGameCount;

    /**
     * 金币
     */
    private Integer gold;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 是否存在污点
     */
    private Boolean blemish;

}
