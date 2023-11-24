package cn.wyz.mapper.bean.dto;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author zhouzhitong
 * @since 2022/09/14
 */
@Setter
@Getter
@ToString
public class BaseDTO extends ExtensibleProperties {

    @Serial
    private static final long serialVersionUID = 6328681439535050530L;

    /**
     * 主键 id（唯一标识）
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    protected Long createdBy;

    /**
     * 修改者
     */
    protected Long lastModifiedBy;

    /**
     * 版本
     */
    @Getter
    @Setter
    private long version;

}
