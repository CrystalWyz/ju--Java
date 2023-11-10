package cn.wyz.murdermystery.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wyz
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MurderMysteryParticipateDTO", description = "聚--参与剧本杀dto")
public class MurderMysteryParticipateDTO {

    private Long murderMysteryId;

    private Long userId;
}
