package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.murdermystery.type.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MurderMysteryPageRequest", description = "剧本杀分页查询请求")
public class MurderMysteryRequest extends BaseRequest {

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 剧本杀状态
     */
    private GameStatus status;

    /**
     * 剧本杀标题
     */
    private String title;
}
