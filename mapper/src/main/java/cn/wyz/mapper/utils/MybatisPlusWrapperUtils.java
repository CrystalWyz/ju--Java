package cn.wyz.mapper.utils;

import cn.wyz.common.exception.AppException;
import cn.wyz.mapper.bean.dto.Sort;
import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.type.QueryType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhouzhitong
 * @since 2023/2/13
 */
@Service
@Slf4j
public class MybatisPlusWrapperUtils {

    public static <T> QueryWrapper<T> simpleQuery() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
//        wrapper.eq("deleted", 0);
        return wrapper;
    }

    public static <T> QueryWrapper<T> simpleQuery(@Nullable BaseRequest query) {
        QueryWrapper<T> wrapper = simpleQuery();
        return buildQueryWrapper(wrapper, query);
    }

    private static <T> QueryWrapper<T> buildQueryWrapper(QueryWrapper<T> wrapper, BaseRequest query) {
        if (query == null) {
            return wrapper;
        }

        // 1. 处理排序参数
        List<Sort> sorts = query.getSorts();
        sorts.forEach(sort -> {
            if (sort.isAsc()) {
                wrapper.orderByAsc(sort.getColumn());
            } else {
                wrapper.orderByDesc(sort.getColumn());
            }
        });

        // 2. 处理字段查询列表参数(这个可以满足大部分的查询需求)
        List<FiledQuery> fieldQueries = query.getFiledQueries();
        fieldQueries.forEach(filedQuery -> {
            String fieldName = filedQuery.getFiledName();
            Object value = filedQuery.getValue();
            QueryType type = filedQuery.getType();
            switch (type) {
                case EQ -> wrapper.eq(fieldName, value);
                case NE -> wrapper.ne(fieldName, value);
                case GT -> wrapper.gt(fieldName, value);
                case GE -> wrapper.ge(fieldName, value);
                case LT -> wrapper.lt(fieldName, value);
                case LE -> wrapper.le(fieldName, value);
                case LIKE -> wrapper.like(fieldName, value);
                case IN -> wrapper.in(fieldName, value);
                case NOT_IN -> wrapper.notIn(fieldName, value);
                case IS_NULL -> wrapper.isNull(fieldName);
                case IS_NOT_NULL -> wrapper.isNotNull(fieldName);
                // 还没做
                default -> throw new AppException();
            }
        });

        // 3. 处理额外参数
        Class<? extends BaseRequest> c = query.getClass();
        if (c != BaseRequest.class) {
            Field[] declaredFields = c.getDeclaredFields();
            for (Field field : declaredFields) {
                String name = field.getName();
                try {
                    field.setAccessible(true);
                    Object value = field.get(query);
                    if (value != null) {
                        wrapper.eq(name, value);
                    }
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    LOGGER.error("buildQueryWrapper error", e);
                }
            }
        }
        // 4. 处理额外参数
        if (!query.extraProperties().isEmpty()) {
            query.extraProperties().forEach(wrapper::eq);
        }
        return wrapper;
    }
}

