package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 缺席记录表
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@ToString(callSuper = true)
@Schema(name = "BlemishDetail对象", description = "缺席记录表")
public class BlemishDetail extends BaseEntity {

    @Schema(name = "用户id")
    private Long userId;

    @Schema(name = "污点类型")
    private Short type;

    @Schema(name = "其他——类型描述")
    private String otherDescription;

    @Schema(name = "描述信息")
    private String description;

    /**
     * 聚id
     *
     * @see JuInfo#getId()
     */
    @Schema(name = "聚id")
    private Long juInfoId;
}
