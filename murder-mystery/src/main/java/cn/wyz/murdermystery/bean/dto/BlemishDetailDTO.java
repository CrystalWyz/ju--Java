package cn.wyz.murdermystery.bean.dto;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "JuInfoDTO", description = "")
public class BlemishDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
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
