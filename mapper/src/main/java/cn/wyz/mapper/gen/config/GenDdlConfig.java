package cn.wyz.mapper.gen.config;

import cn.wyz.common.base.BaseEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouzhitong
 * @since 2023-11-12
 **/
public class GenDdlConfig {

    /**
     * 字段类型映射
     */
    @Getter
    private final Map<Class<?>, FieldTypeMap> fieldTypeMap;

    public GenDdlConfig() {
        this.fieldTypeMap = new HashMap<>();
        init();
    }

    public void init() {
        // 初始化 常见的Bean实体字段类型与 postgresql 数据库字段类型的映射
        fieldTypeMap.put(Long.class, new FieldTypeMap(Long.class, "bigint"));
        fieldTypeMap.put(Integer.class, new FieldTypeMap(Integer.class, "integer"));
        fieldTypeMap.put(Short.class, new FieldTypeMap("smallint"));
        fieldTypeMap.put(String.class, new FieldTypeMap(String.class, "varchar(64)"));
        fieldTypeMap.put(LocalDateTime.class, new FieldTypeMap(LocalDateTime.class, "timestamp(3)"));
        fieldTypeMap.put(Boolean.class, new FieldTypeMap(Boolean.class, "boolean"));
        fieldTypeMap.put(Double.class, new FieldTypeMap(Double.class, "double precision"));

        FieldTypeMap list = new FieldTypeMap(List.class, "jsonb");
        list.add(Long.class, "bigint[]");
        list.add(Integer.class, "int[]");
        list.add(String.class, "jsonb");
        fieldTypeMap.put(List.class, list);
        fieldTypeMap.put(JSONObject.class, new FieldTypeMap(JSONObject.class, "jsonb"));
        fieldTypeMap.put(com.alibaba.fastjson2.JSONObject.class, new FieldTypeMap("jsonb"));
        fieldTypeMap.put(BaseEnum.class, new FieldTypeMap(BaseEnum.class, "integer"));
        fieldTypeMap.put(Enum.class, new FieldTypeMap(BaseEnum.class, "integer"));
    }

    public FieldTypeMap get(Class<?> c) {
        if (c == null) {
            return fieldTypeMap.get(null);
        }
        if (Object.class == c) {
            return null;
        }
        FieldTypeMap res;
        if ((res = fieldTypeMap.get(c)) != null) {
            return res;
        }
        Class<?>[] interfaces = c.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            res = get(anInterface);
            if (res != null) {
                return res;
            }
        }
        return get(c.getSuperclass());
    }


}
