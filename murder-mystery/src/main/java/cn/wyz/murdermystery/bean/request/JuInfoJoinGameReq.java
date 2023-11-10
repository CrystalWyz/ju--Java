package cn.wyz.murdermystery.bean.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhouzhitong
 * @since 2023-11-09
 **/
@Data
public class JuInfoJoinGameReq implements Serializable {

    private Long juInfoId;

    private Long userId;

    private String applyReason;

}
