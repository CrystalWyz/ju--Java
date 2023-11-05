package cn.wyz.murdermystery.bean.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 剧本杀退出游戏请求参数
 *
 * @author zhouzhitong
 * @since 2023-11-05
 **/
@Data
public class JuInfoOutGameReq implements Serializable {

    /**
     * 聚Id
     */
    private Long juInfoId;

    /**
     * 操作用户Id
     */
    private Long userId;

    /**
     * 是否强制退出
     */
    private Boolean isForce;

    /**
     * 强制退出理由
     */
    private String reason;

}
