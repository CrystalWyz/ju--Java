package cn.wyz.app.gen;

import cn.wyz.app.gen.config.FieldTypeMap;
import cn.wyz.app.gen.config.GenDdlConfig;
import cn.wyz.app.gen.utils.PackageUtil;
import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouzhitong
 * @since 2023-11-12
 **/
@Service
@Slf4j
public class DdlSqlCreateService {
    private final GenDdlConfig config = new GenDdlConfig();

    @Value("${ju.ddl-create.genFilePath: /config/sql/ddl.sql}")
    private String ddlSqlPath;

    @Value("${ju.ddl-create.isCreateAllDdlSql: false}")
    private Boolean isCreateAllDdlSql;

    @PostConstruct
    public void init() {
        if (!isCreateAllDdlSql) {
            LOGGER.info("自动生成DDL SQL功能未开启. 如果需要该功能, 请添加配置 'ju.ddl-create.isCreateAllDdlSql: true'");
            return;
        }
        // 获取启动目录
        String userDir = System.getProperty("user.dir");
        // 如果前缀没有 / 则添加
        if (!ddlSqlPath.startsWith("/")) {
            userDir += "/";
        }
        String filePath = userDir + ddlSqlPath;

        BufferedWriter bw = getFileOutputStream(filePath);


        List<Class<?>> subClasses = PackageUtil.getSubClasses(BaseEntity.class);
        for (Class<?> subClass : subClasses) {
            String ddlSql = createDdlSql((Class<? extends BaseEntity>) subClass);
            try {
                bw.write("-- " + subClass.getName() + "\n");
                bw.write(ddlSql);
                bw.write("\n\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createAllDdlSql() {
        List<Class<?>> subClasses = PackageUtil.getSubClasses(BaseEntity.class);
        for (Class<?> subClass : subClasses) {
            String ddlSql = createDdlSql((Class<? extends BaseEntity>) subClass);
            System.out.println(ddlSql);
        }
    }

    public String createDdlSql(Class<? extends BaseEntity> c) {
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
                throw new RuntimeException("未知的子字段类型: " + type + " subType: " + subType);
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
        sql.append(genBaseDdlSqlComment(tableName));

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

    private String genBaseDdlSql() {
        return """
                    id bigserial primary key,
                    create_time timestamp(3) not null default now(),
                    update_time timestamp(3) not null default now(),
                    created_by varchar(64) not null default 'system',
                    last_modified_by varchar(64) not null default 'system',
                """;
    }

    private String genBaseDdlSqlComment(String tableName) {
        return "comment on column " + tableName + ".id is '主键';" + "\n" +
                "comment on column " + tableName + ".create_time is '创建时间';" + "\n" +
                "comment on column " + tableName + ".update_time is '更新时间';" + "\n" +
                "comment on column " + tableName + ".created_by is '创建人';" + "\n" +
                "comment on column " + tableName + ".last_modified_by is '最后修改人';" + "\n";
    }

    private String getTableComment(Class<? extends BaseEntity> c) {
        Description annotation = c.getAnnotation(Description.class);
        if (annotation == null) {
            return "";
        }
        return annotation.value();
    }

    private String getTableName(Class<? extends BaseEntity> c) {
        TableName annotation = c.getAnnotation(TableName.class);
        if (annotation == null || StringUtils.isBlank(annotation.value())) {
            // 类名的驼峰转下划线
            String name = c.getSimpleName();
            return getUnderlineName(name);
        }

        return annotation.value();
    }

    private List<Field> getAllFields(Class<?> c) {
        List<Field> allFields = new LinkedList<>();
        while (c != BaseEntity.class) {
            Field[] fields = c.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            c = c.getSuperclass();
        }
        return allFields;
    }

    // Ljava/util/List<Ljava/lang/Long;>;
    private Class<?> getGenericTypeName(Field field) {
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
    private String getUnderlineName(String name) {
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

    private BufferedWriter getFileOutputStream(String filePath) {
        File file = new File(filePath);
        // 判断文件是否存在, 不存在就创建, 如果存在就清空里面的内容
        if (file.exists()) {
            try {
                if (!file.delete()) {
                    throw new RuntimeException("清空文件失败: " + filePath);
                }
                if (!file.createNewFile()) {
                    throw new RuntimeException("创建文件失败: " + filePath);
                }
                return new BufferedWriter(new FileWriter(file));
            } catch (Exception e) {
                throw new RuntimeException("清空文件失败: " + filePath);
            }
        } else {
            try {

                if (!file.createNewFile()) {
                    throw new RuntimeException("创建文件失败: " + filePath);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                return new BufferedWriter(new FileWriter(file));
            } catch (Exception e) {
                throw new RuntimeException("创建文件失败: " + filePath);
            }
        }

    }

}
