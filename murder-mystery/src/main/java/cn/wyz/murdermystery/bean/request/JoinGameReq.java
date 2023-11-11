package cn.wyz.murdermystery.bean.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 加入游戏请求参数
 *
 * @author zhouzhitong
 * @since 2023-11-09
 **/
@Data
public class JoinGameReq implements Serializable {

    /**
     * 游戏Id
     */
    private Long gameId;

    /**
     * 操作用户Id
     */
    private Long userId;

    /**
     * 申请理由/撤销理由
     */
    private String reason;

}
