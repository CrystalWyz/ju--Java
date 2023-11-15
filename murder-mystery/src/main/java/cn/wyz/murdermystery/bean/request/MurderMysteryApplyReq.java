package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.type.ApplyStatus;
import lombok.Data;

/**
 * 剧本杀申请请求参数
 *
 * @author zhouzhitong
 * @since 2023-11-15
 **/
@Data
public class MurderMysteryApplyReq extends BaseRequest {

    /**
     * 申请人
     *
     * @see cn.wyz.user.bean.User User#id
     */
    private Long userId;

    /**
     * 关联剧本杀记录
     *
     * @see MurderMystery#getId() MurderMystery#id
     */
    private Long gameId;

    /**
     * 申请状态
     *
     * @see ApplyStatus ApplyStatus
     */
    private ApplyStatus applyStatus;

    /**
     * 申请理由
     */
    private String applyReason;

    /**
     * 拒绝理由
     */
    private String rejectReason;

}
