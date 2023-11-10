package cn.wyz.murdermystery.bean;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz
 */
@Data
public class MurderMystery {

    private Long id;

    private String title;

    private LocalDateTime beginExpected;

    private LocalDateTime finishExpected;

    private LocalDateTime beginActual;

    private LocalDateTime finishActual;

    private LocalDateTime createTime;

    private String description;

    private Long userId;

    private LocalDateTime updateTime;

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
