package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
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
@Schema(name = "MurderMysteryPageRequest", description = "剧本杀游戏用户查询请求")
public class MurderMysteryUserRequest extends BaseRequest {

    private Long userId;

}
