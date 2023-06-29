package cn.wyz.common.bean.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnx
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PageVM", description = "分页查询请求")
public class PageVM<T> {

    private int pageSize;

    private int pageNum;

    private T condition;
}
