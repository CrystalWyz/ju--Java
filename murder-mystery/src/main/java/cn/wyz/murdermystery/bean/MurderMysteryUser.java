package cn.wyz.murdermystery.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MurderMysteryUser {

    private Long id;

    private Long userId;

    private Integer participateNum;

    private Integer quitMidwayNum;

    private Integer level;

    private List<String> tags;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
