package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.murdermystery.type.ServiceType;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "tag", autoResultMap = true)
public class Tag extends BaseEntity {

    /**
     * 标记名称
     */
    private String name;

    /**
     * 标记类型
     */
    private Integer type;

    /**
     * 业务类型
     */
    private ServiceType serviceType;

}
