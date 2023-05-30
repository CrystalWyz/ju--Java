package cn.wyz.murdermystery.bean.dto;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "JuInfoDTO", description = "")
public class BlemishDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
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

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("聚id")
    private Long juInfoId;
}
