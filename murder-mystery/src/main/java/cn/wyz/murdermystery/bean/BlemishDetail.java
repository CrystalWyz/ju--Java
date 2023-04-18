package cn.wyz.murdermystery.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@ApiModel(value = "BlemishDetail对象", description = "")
public class BlemishDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("污点类型")
    private Short type;

    @ApiModelProperty("其他——类型描述")
    private String otherDescription;

    @ApiModelProperty("描述信息")
    private String description;
}
