package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 剧本杀用户信息
 *
 * @author wyz
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "murder_mystery_user", autoResultMap = true)
public class MurderMysteryUser extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 游戏等级
     */
    private Integer grade;

    /**
     * 参加游戏场数
     */
    private Integer count;

    /**
     * 污点游戏场数
     */
    private Integer blemishCount;

}
