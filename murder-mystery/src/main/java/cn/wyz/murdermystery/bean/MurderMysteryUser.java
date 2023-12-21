package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wyz
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "murder_mystery_user", autoResultMap = true)
public class MurderMysteryUser extends BaseEntity {

    private Long userId;
}
