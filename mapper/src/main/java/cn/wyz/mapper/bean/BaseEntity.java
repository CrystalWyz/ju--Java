package cn.wyz.mapper.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
