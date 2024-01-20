package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.constant.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "MurderMysteryUserDTO", description = "剧本杀用户信息")
public class MurderMysteryUserDTO extends BaseDTO {

    @Schema(name = "userId", description = "用户id")
    private Long userId;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "gender", description = "性别")
    private Gender gender;

    @Schema(name = "grade", description = "游戏等级")
    private Integer grade;

    @Schema(name = "count", description = "参与次数")
    private Integer count;

    @Schema(name = "blemishCount", description = "污点数")
    private Integer blemishCount;

}
