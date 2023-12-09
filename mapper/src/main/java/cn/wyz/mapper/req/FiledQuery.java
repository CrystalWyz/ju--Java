package cn.wyz.mapper.req;

import cn.wyz.mapper.type.QueryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhouzhitong
 * @since 2023/5/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiledQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字段名
     */
    private String filedName;

    /**
     * 字段值
     */
    private Object value;

    /**
     * 查询类型
     */
    private QueryType type = QueryType.EQ;

    public static FiledQuery of(String filedName, Object value, QueryType type) {
        FiledQuery filedQuery = new FiledQuery();
        filedQuery.setFiledName(filedName);
        filedQuery.setValue(value);
        filedQuery.setType(type);
        return filedQuery;
    }

}
