package cn.wyz.mapper.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import lombok.extern.slf4j.Slf4j;

/**
 * Postgre 数据库分页语句组装实现
 *
 * @author hubin
 * @since 2016-01-23
 */
@Slf4j
public class PostgreDialect implements IDialect {

    @Override
    public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
//        offset = limit * offset;
        LOGGER.trace("originalSql {} \n offset {}, limit {}", originalSql, offset, limit);
        StringBuilder sql = new StringBuilder(originalSql).append(" LIMIT ").append(FIRST_MARK);
        if (offset != 0L) {
            sql.append(" OFFSET ").append(SECOND_MARK);
            return new DialectModel(sql.toString(), limit, offset).setConsumerChain();
        } else {
            return new DialectModel(sql.toString(), limit).setConsumer(true);
        }
    }
}

