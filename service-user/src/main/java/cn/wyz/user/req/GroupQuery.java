package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (Group) 请求参数
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:29
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GroupQuery extends BaseRequest {

    private String name;
    private String description;
    private String avatar;
}
