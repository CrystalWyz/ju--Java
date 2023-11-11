package cn.wyz.murdermystery.bean.bo;

import cn.wyz.mapper.bean.bo.BaseBO;
import cn.wyz.murdermystery.type.GameStatus;
import com.alibaba.fastjson2.JSONObject;
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
public class MurderMysteryBO extends BaseBO {

    private String title;

    private GameStatus status;

    private LocalDateTime beginExpected;

    private LocalDateTime finishExpected;

    private LocalDateTime beginActual;

    private LocalDateTime finishActual;

    private String description;

    private Long userId;

    private Integer scale;

    private Integer girlParticipantNum;

    private Integer boyParticipantNum;

    private JSONObject config;

    private List<Long> girlParticipant;

    private List<Long> boyParticipant;

    private List<Integer> area;

    private String address;

    private String shopName;
}
