package cn.wyz.murdermystery.bean.dto;

import cn.wyz.mapper.bean.dto.BaseDTO;
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
@Schema(name = "MurderMysteryUserDTO", description = "")
public class MurderMysteryUserDTO extends BaseDTO {

    private Long userId;

    /**
     * 游戏等级
     */
    private Integer grade;

    /**
     * 参加游戏场数
     */
    private Integer count;

    /**
     * 污点游戏场数
     */
    private Integer blemishCount;

}
