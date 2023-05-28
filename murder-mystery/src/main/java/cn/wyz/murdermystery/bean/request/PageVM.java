package cn.wyz.murdermystery.bean.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnx
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageVM", description = "分页查询请求")
public class PageVM<T> {

    private Integer pi;

    private Integer ps;

    private T condition;
}
