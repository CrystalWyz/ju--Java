package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhouzhitong
 * @since 2023-11-05
 **/
@Data
@ToString(callSuper = true)
public class BlemishDetailRequest extends BaseRequest {

    /**
     * 用户id
     */
    private Long userId;

}
