package cn.wyz.user.req;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.type.QueryType;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 用户表(User) 请求参数
 *
 * @author zhouzhitong
 * @since 2023-05-31 13:14:56
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UserQuery extends BaseRequest {

    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否启用 0-禁用，1-启用
     */
    private String enabled;


    public static UserQuery findByUsername(String username) {
        UserQuery query = new UserQuery();
        List<FiledQuery> filedQueries = Lists.newArrayList();
        FiledQuery filedQuery = new FiledQuery();
        filedQuery.setFiledName("username");
        filedQuery.setValue(username);
        filedQuery.setType(QueryType.EQ);
        filedQueries.add(filedQuery);
        query.setFiledQueries(filedQueries);
        return query;
    }

}
