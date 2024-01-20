package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.type.ApplyStatus;
import lombok.*;

/**
 * 剧本杀申请记录
 *
 * @author zhouzhitong
 * @since 2023-11-06
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MurderMysteryApplyDTO extends BaseDTO {

    /**
     * 关联剧本杀记录
     *
     * @see MurderMystery#getId() JuInfo#id
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
