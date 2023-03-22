package cn.wyz.murdermystery.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Getter
@Setter
@TableName("blemish_detail")
@ApiModel(value = "BlemishDetail对象", description = "")
public class BlemishDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("污点类型")
    @TableField("type")
    private Short type;

    @ApiModelProperty("其他——类型描述")
    @TableField("other_description")
    private String otherDescription;

    @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;
}
