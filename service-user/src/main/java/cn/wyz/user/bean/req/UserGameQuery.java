package cn.wyz.user.bean.req;

import cn.wyz.mapper.req.BaseRequest;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户游戏属性
 *
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_game")
public class UserGameQuery extends BaseRequest {

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
