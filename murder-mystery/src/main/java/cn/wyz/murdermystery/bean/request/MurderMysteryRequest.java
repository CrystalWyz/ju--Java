package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.murdermystery.type.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author wyz
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MurderMysteryPageRequest", description = "剧本杀分页查询请求")
public class MurderMysteryRequest extends BaseRequest {

    /**
     * 游戏编号 id
     */
    private Long id;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 剧本杀状态
     */
    private GameStatus status;

    /**
     * 剧本杀标题
     */
    private String title;

    /**
     * 操作人
     */
    private Long userId;

    /**
     * 是否满员
     */
    private boolean full = false;

    /**
     * 缺席人员
     */
    private List<Long> absentees;

    /**
     * 到场人员
     */
    private List<Long> attendees;


}
