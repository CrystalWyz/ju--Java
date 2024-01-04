package cn.wyz.murdermystery.bean.request;

import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.murdermystery.type.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

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

    public static TagRequest findInByNames(List<String> names) {
        TagRequest req = new TagRequest();
        FiledQuery fq = new FiledQuery();
        fq.setFiledName("name");
        fq.setType(QueryType.IN);
        fq.setValue(names);
        req.getFiledQueries().add(fq);
        return req;
    }

}
