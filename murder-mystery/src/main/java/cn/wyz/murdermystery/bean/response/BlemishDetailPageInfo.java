package cn.wyz.murdermystery.bean.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserPageInfo", description = "用户分页信息")
public class BlemishDetailPageInfo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("污点类型")
    private Short type;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
