package cn.wyz.mapper.bean;

import cn.wyz.mapper.autoGen.anno.FieldDescription;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class BaseEntity extends Model<BaseEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8328293151203544834L;

    /**
     * 主键 ID
     */
    @TableId(type = IdType.AUTO)
    @FieldDescription(comment = "主键 ID")
    protected Long id;

    /**
     * 创建时间
     */
    @FieldDescription(comment = "创建时间", defaultValue = "CURRENT_TIMESTAMP", isAllowEmpty = false)
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    @OrderBy
    @FieldDescription(comment = "更新时间", defaultValue = "CURRENT_TIMESTAMP", isAllowEmpty = false)
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
    @FieldDescription(comment = "创建人", defaultValue = "system", length = 64, isAllowEmpty = false)
    protected Long createdBy = 1L;

    /**
     * 更新人
     */
    @TableField
    @FieldDescription(comment = "更新人", defaultValue = "system", isAllowEmpty = false)
    protected Long lastModifiedBy = 1L;

//    /**
//     * 版本
//     */
//    @Setter
////    @Version
//    private long version = 0;

//    /**
//     * 软删除标识 0-未删除 1-已删除
//     */
//    protected Integer deleted = 0;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }

    public long getAndIncrementVersion() {
        return 1L;
//        return ++version;
    }

}
