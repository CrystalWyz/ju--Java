package cn.wyz.app.gen;

import cn.wyz.app.gen.config.FieldTypeMap;
import cn.wyz.app.gen.config.GenDdlConfig;
import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.murdermystery.bean.MurderMystery;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.context.annotation.Description;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * sadasd
 *
 * @author zhouzhitong
 * @since 2023-11-12
 **/
public class DemoSQLCreate {

    private static final GenDdlConfig config = new GenDdlConfig();


    public static void main(String[] args) {
        Class<MurderMystery> c = MurderMystery.class;
        System.out.println(genDDL_SQL(c));
    }

    /**
     * 生成 postgresql 数据库的建表语句 ddl sql
     *
     * @param c 实体类
     * @return ddl sql
     */
    private static String genDDL_SQL(Class<? extends BaseEntity> c) {
        StringBuilder sql = new StringBuilder();

        String tableName = getTableName(c);
        sql.append("CREATE TABLE ").append(tableName).append(" (").append("\n");
        sql.append(genBaseDdlSql());

        List<Field> allFields = getAllFields(c);
        int lastIndex = allFields.size() - 1;
        for (Field field : allFields) {
            Class<?> type = field.getType();
            FieldTypeMap fieldTypeMap = config.get(type);
            if (fieldTypeMap == null) {
                throw new RuntimeException("未知的字段类型: " + type);
            }
            Class<?> subType = getGenericTypeName(field);

            String typeStr = fieldTypeMap.getType(subType);
            if (typeStr == null) {
                throw new RuntimeException("未知的字段类型: " + type);
            }
            String fieldName = getUnderlineName(field.getName());
            sql.append("\t").append(fieldName).append(" ").append(typeStr);
            if (lastIndex-- > 0) {
                sql.append(",");
            }
            sql.append("\n");
        }
        sql.append(");").append("\n");

        // 表名注释, 从类的注释中获取
        String tableComment = getTableComment(c);
        if (!tableComment.isBlank()) {
            sql.append("comment on table ").append(tableName).append(" is '").append(tableComment).append("';").append("\n");
        }
        // 开始添加注释
        sql.append(genBaseDdlSqlComment());

        // TODO 字段注释, 从字段的注释中获取
//        for (Field allField : allFields) {
//            String fieldName = getUnderlineName(allField.getName());
//            String comment = allField.getAnnotation(TableField.class).value();
//            if (!comment.isBlank()) {
//                sql.append("comment on column ").append(tableName).append(".").append(fieldName).append(" is '").append(comment).append("';").append("\n");
//            }
//        }

        return sql.toString();
    }

    private static String genBaseDdlSql() {
        return """
                    id bigserial primary key,
                    create_time timestamp(3) not null default now(),
                    update_time timestamp(3) not null default now(),
                    created_by varchar(64) not null default 'system',
                    last_modified_by varchar(64) not null default 'system',
                """;
    }

    private static String genBaseDdlSqlComment() {
        return """
                comment on column id is '主键id';
                comment on column create_time is '创建时间';
                comment on column update_time is '更新时间';
                comment on column created_by is '创建人';
                comment on column last_modified_by is '最后修改人';
                """;
    }

    private static String getTableComment(Class<? extends BaseEntity> c) {
        Description annotation = c.getAnnotation(Description.class);
        if (annotation == null) {
            return "";
        }
        return annotation.value();
    }

    private static String getTableName(Class<? extends BaseEntity> c) {
        TableName annotation = c.getAnnotation(TableName.class);
        if (annotation == null) {
            // 类名的驼峰转下划线
            String name = c.getSimpleName();
            return getUnderlineName(name);
        }

        return annotation.value();
    }

    private static List<Field> getAllFields(Class<?> c) {
        List<Field> allFields = new LinkedList<>();
        while (c != BaseEntity.class) {
            Field[] fields = c.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            c = c.getSuperclass();
        }
        return allFields;
    }

    // Ljava/util/List<Ljava/lang/Long;>;
    private static Class<?> getGenericTypeName(Field field) {
        Type genericType = field.getGenericType();
        String signature = genericType.getTypeName();
        // 如果没有 < 和 > 返回空
        if (!signature.contains("<") && !signature.contains(">")) {
            return null;
        }
        int begin = signature.indexOf("<");
        int end = signature.indexOf(">");
        String substring = signature.substring(begin + 1, end);
        try {
            return Class.forName(substring);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符转换成驼峰命名(小写+下划线)
     */
    private static String getUnderlineName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toLowerCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成小写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }

}
