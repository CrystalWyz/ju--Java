package cn.wyz.murdermystery.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@Schema(name = "BlemishDetail对象", description = "")
public class BlemishDetail {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "用户id")
    private Long userId;

    @Schema(name = "污点类型")
    private Short type;

    @Schema(name = "其他——类型描述")
    private String otherDescription;

    @Schema(name = "描述信息")
    private String description;

    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    @Schema(name = "聚id")
    private Long juInfoId;
}
