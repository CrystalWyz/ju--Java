package cn.wyz.murdermystery.bean.bo;

import cn.wyz.mapper.bean.bo.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouzhitong
 * @since 2023-12-09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MurderMysteryJoinBO extends BaseBO {

    /**
     * 是否可以加入
     */
    private boolean canJoin = true;

    /**
     * 不能加入的原因
     */
    private String reason;

    /**
     * 不能加入的元数据
     * <p>
     * 目前可能是:
     * {@link cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO MurderMysteryApplyDTO}
     * 或 {@link cn.wyz.murdermystery.bean.dto.MurderMysteryDTO MurderMysteryDTO}
     * 或 {@link cn.wyz.murdermystery.bean.MurderMystery MurderMyster}
     */
    private Object source;

    public static MurderMysteryJoinBO of(Object source, String reason) {
        return new MurderMysteryJoinBO(false, reason, source);

    }


}
