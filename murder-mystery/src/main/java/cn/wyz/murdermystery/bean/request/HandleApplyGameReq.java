package cn.wyz.murdermystery.bean.request;

import cn.wyz.murdermystery.type.ApplyStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * 处理申请请求参数
 *
 * @author zhouzhitong
 * @since 2023-11-09
 **/
@Data
public class HandleApplyGameReq implements Serializable {

    /**
     * 游戏Id
     */
    private Long gameId;

    /**
     * 操作用户Id
     */
    private Long userId;

    /**
     * 申请记录Id
     */
    private Long applyId;

    /**
     * 修改后的申请状态
     */
    private ApplyStatus status;

    /**
     * 拒绝理由
     */
    private String reason;

}
