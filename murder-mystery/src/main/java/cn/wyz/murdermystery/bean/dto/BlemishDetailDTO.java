package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "JuInfoDTO", description = "")
public class BlemishDetailDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "用户id")
    private Long userId;

    @Schema(name = "污点类型")
    private Short type;

    @Schema(name = "其他——类型描述")
    private String otherDescription;

    @Schema(name = "描述信息")
    private String description;

    @Schema(name = "聚id")
    private Long juInfoId;
}
