package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.murdermystery.type.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class TagRequest extends BaseRequest {

    /**
     * 标记名称
     */
    @Schema(name = "name", description = "标记名称")
    private String name;

    /**
     * 标记类型
     */
    @Schema(name = "type", description = "标记类型")
    private Integer type;

    /**
     * 业务类型
     */
    @Schema(name = "serviceType", description = "业务类型")
    private ServiceType serviceType;

}
