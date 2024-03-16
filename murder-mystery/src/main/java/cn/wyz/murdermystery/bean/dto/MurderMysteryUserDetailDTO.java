package cn.wyz.murdermystery.bean.dto;

import cn.wyz.murdermystery.type.BlemishDetailType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author zhouzhitong
 * @since 2024-03-16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MurderMysteryUserDetailDTO {

    @Schema(name = "userId", description = "用户id")
    private Long userId;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "grade", description = "游戏等级")
    private Integer grade;

    @Schema(name = "count", description = "参与次数")
    private Integer count;

    @Schema(name = "blemishCount", description = "污点总数")
    private Integer blemishCount;

    @Schema(name = "blemishCountMap", description = "污点类型的场数")
    private Map<BlemishDetailType, Long> blemishCountMap;

    public void setMMUser(MurderMysteryUserDTO mmUser) {
        this.userId = mmUser.getUserId();
        this.username = mmUser.getUsername();
        this.grade = mmUser.getGrade();
        this.count = mmUser.getCount();
        this.blemishCount = mmUser.getBlemishCount();
    }

}
