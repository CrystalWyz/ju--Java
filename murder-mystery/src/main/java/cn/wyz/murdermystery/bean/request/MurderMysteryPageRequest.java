package cn.wyz.murdermystery.bean.request;

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
public class MurderMysteryPageRequest {

    private String title;
}
