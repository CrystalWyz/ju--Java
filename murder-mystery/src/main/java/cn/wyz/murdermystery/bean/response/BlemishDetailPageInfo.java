package cn.wyz.murdermystery.bean.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserPageInfo", description = "用户分页信息")
public class BlemishDetailPageInfo {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "污点类型")
    private Short type;

    @Schema(name = "创建时间")
    private LocalDateTime createTime;
}
