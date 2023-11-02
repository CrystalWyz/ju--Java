package cn.wyz.murdermystery.bean;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
public class JuInfo {

    private Long id;

    private Long userId;

    private String title;

    private Integer type;

    private List<Long> participant;

    private Integer scale;

    private Integer status;

    private Integer girlParticipantNum;

    private Integer boyParticipantNum;

    private LocalDateTime begin;

    private LocalDateTime finish;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private JSONObject config;
}
