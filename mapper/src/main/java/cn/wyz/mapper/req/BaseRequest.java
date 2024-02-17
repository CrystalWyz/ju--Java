package cn.wyz.mapper.req;

import cn.wyz.mapper.bean.dto.ExtensibleProperties;
import cn.wyz.mapper.bean.dto.Sort;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.mapper.utils.MybatisPlusWrapperUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 查询基础参数
 *
 * @author zhouzhitong
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class BaseRequest extends ExtensibleProperties implements Serializable {

    @Serial
    private static final long serialVersionUID = -5829984477493358777L;

    /**
     * 页码, 默认第一页
     */
    protected Integer page = 1;

    /**
     * 每页几条数据, 默认 10个
     */
    protected Integer size = 10;

    /**
     * 排序列表
     */
    private List<Sort> sorts;

    /**
     * 字段查询列表
     * <p>
     * 用于构建查询条件, 这个有丰富的匹配方式， 可以满足大部分的查询需求; 也可以自定义查询条件, 但是那个查询条件需要自己写sql
     *
     * @see MybatisPlusWrapperUtils MybatisWrapperUtils
     */
    private List<FiledQuery> filedQueries;

    public boolean isPage() {
        return page != null || size != null;
    }

    public List<Sort> getSorts() {
        return sorts != null ? sorts : Collections.emptyList();
    }

    public List<FiledQuery> getFiledQueries() {
        if (filedQueries == null) {
            this.filedQueries = Lists.newArrayList();
        }
        return filedQueries;
    }

    /**
     * 构建分页对象
     *
     * @param <Entity> 实体类型
     * @return 分页对象
     */
    public <Entity> Page<Entity> buildPage() {
//        return new Page<>(page - 1, size, true);
        return new Page<>((long) page * size, size, true);
    }

    public void setSorts(String sorts) {
        this.sorts = Lists.newArrayList();
        for (String sort : sorts.split(",")) {
            String[] split = sort.split(" ");
            this.sorts.add(Sort.of(split[0], split[1]));
        }
    }

    public void setQueries(String filedQueries) {
        if (filedQueries == null) {
            this.filedQueries = Lists.newArrayList();
        }
        for (String query : filedQueries.split(",")) {
            String[] s = query.split(":");
            // 0: 字段名, 1: 值, 2: 查询类型
            FiledQuery filedQuery = FiledQuery.of(s[0], s[1], QueryType.of(Integer.parseInt(s[2])));
            this.filedQueries.add(filedQuery);
        }
    }

    public Integer size() {
        return size;
    }

    public Integer page() {
        return page;
    }

}
