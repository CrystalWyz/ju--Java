package cn.wyz.app.gen.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouzhitong
 * @since 2023-11-12
 **/
public class FieldTypeMap {

    /**
     * 字段类型
     */
    private Class<?> fieldType;

    /**
     * key: 该字段的子类型, 如果没有就是null
     * value: 数据库字段类型
     */
    private Map<Class<?>, String> subTypeMap = new HashMap<>();

    public FieldTypeMap(Class<?> fieldType, Map<Class<?>, String> subTypeMap) {
        this.fieldType = fieldType;
        this.subTypeMap = subTypeMap;
    }

    public FieldTypeMap(Class<?> fieldType, String type) {
        this.fieldType = fieldType;
        this.subTypeMap.put(null, type);
    }

    public FieldTypeMap(String type) {
        this.subTypeMap.put(null, type);
    }

    public void add(Class<?> subType, String type) {
        this.subTypeMap.put(subType, type);
    }

    public String getType(Class<?> subType) {
        return subTypeMap.get(subType);

    }

}
