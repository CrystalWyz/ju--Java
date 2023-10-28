package cn.wyz.mapper.vo;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.mapper.bean.dto.PageInfo;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;

/**
 * 分页查询返回接口
 *
 * @author zhouzhitong
 * @version 1.0
 * @since 2022/6/18
 */
@Getter
@ToString(callSuper = true)
public class PageResultVO<T> extends ResponseResult<List<T>> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分页信息, 如果是null, 则不分页
     */
    private final PageInfo pageInfo;

    private PageResultVO(List<T> page, PageInfo pageInfo) {
        super(page);
        this.pageInfo = pageInfo;
    }

    public static <T> PageResultVO<T> ok(List<T> dataList, PageInfo pageInfo) {
        return success(dataList, pageInfo);
    }

    public static <T> PageResultVO<T> success(List<T> dataList, PageInfo pageInfo) {
        return new PageResultVO<>(dataList, pageInfo);
    }

}
