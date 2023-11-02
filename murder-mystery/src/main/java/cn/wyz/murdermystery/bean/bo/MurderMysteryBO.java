package cn.wyz.murdermystery.bean.bo;

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
public class MurderMysteryBO {

    private Long id;

    private String title;

    private LocalDateTime begin;

    private LocalDateTime finish;

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
