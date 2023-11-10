package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.type.ApplyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 剧本杀申请记录
 *
 * @author zhouzhitong
 * @since 2023-11-06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JuInfoApplyRequest extends BaseRequest {

    /**
     * 申请人
     *
     * @see cn.wyz.user.bean.User User#id
     */
    private Long userId;

    /**
     * 关联剧本杀记录
     *
     * @see JuInfo#getId() JuInfo#id
     */
    private Long juInfoId;

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
