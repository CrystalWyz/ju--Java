package cn.wyz.murdermystery.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wnx
 */ // 这个 BaseTypeHandler 里的泛型就是你真实要使用的数组类型，这里写成 List<Object> 能少写几个 typeHandler
public class SetTypeHandler extends BaseTypeHandler<Set<Object>> {

    /**
     * 这个是 Java 类型往数据库存储时的处理逻辑
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<Object> parameter, JdbcType jdbcType) throws SQLException {
        // 如果是空数组的话也给它塞个空数组进去，在 PostgreSQL 中是 {} 这种，应该可以根据业务上的需求调整
        if (parameter.isEmpty()) {
            Connection conn = ps.getConnection();
            Array array = conn.createArrayOf(JDBCType.BIGINT.getName(), new Long[0]);
            ps.setArray(i, array);
            array.free();
            return;
        }

        String typeName = null;
        Object eleType = parameter.iterator().next();
        if (eleType instanceof Integer) {
            typeName = JDBCType.INTEGER.getName();
        } else if (eleType instanceof String) {
            typeName = JDBCType.VARCHAR.getName();
        } else if (eleType instanceof Boolean) {
            typeName = JDBCType.BOOLEAN.getName();
        } else if (eleType instanceof Double) {
            typeName = JDBCType.DOUBLE.getName();
        } else if (eleType instanceof Float) {
            typeName = JDBCType.FLOAT.getName();
        } else if (eleType instanceof Long) {
            typeName = JDBCType.BIGINT.getName();
        } else if (eleType instanceof Short) {
            typeName = JDBCType.SMALLINT.getName();
        } else if (eleType instanceof Byte) {
            typeName = JDBCType.TINYINT.getName();
        }
        if (typeName == null) {
            throw new TypeException("ArrayTypeHandler parameter typeName error, your type is " + parameter.getClass().getName());
        }

        Connection conn = ps.getConnection();
        Array array = conn.createArrayOf(typeName, parameter.toArray());
        ps.setArray(i, array);
        array.free();
    }

    @Override
    public Set<Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return getSet(resultSet.getArray(s));
    }

    @Override
    public Set<Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return getSet(resultSet.getArray(i));
    }

    @Override
    public Set<Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return getSet(callableStatement.getArray(i));
    }

    /**
     * 上面都是从数据库读取后存入 Java PO 对象时的处理逻辑
     * 这里写了一个统一的方法，因为逻辑都一样
     */
    private Set<Object> getSet(Array array) {
        // 如果数据库字段为 null，这里会转成一个空的 List，根据业务需求更改，赋值为 null 的话，后面对 PO 处理要注意 NPE
        if (array == null) {
            return Collections.emptySet();
        }
        try {
            // 这里要把 java.sql.Array 转成 java.util.Set，尝试了多种写法，只有 Copilot 写的不会出警告
            Object[] objects = (Object[]) array.getArray();
            array.free();
            return Arrays.stream(objects).collect(Collectors.toSet());
        } catch (Exception ignored) {
        }
        return null;
    }
}