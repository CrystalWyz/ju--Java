package cn.wyz.user.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.bean.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouzhitong
 * @since 2023-10-31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGameDTO extends BaseDTO {

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
