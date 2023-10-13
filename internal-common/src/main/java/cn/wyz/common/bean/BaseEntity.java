package cn.wyz.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhouzhitong
 * @since 2023-10-10
 **/
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8328293151203544834L;

    /**
     * 主键 ID
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
    protected String createdBy = "system";

    /**
     * 更新人
     */
    protected String lastModifiedBy = "system";

    /**
     * 软删除标识 0-未删除 1-已删除
     */
    protected Integer deleted = 0;

}
