package cn.wyz.murdermystery.mapper;

import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.JuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Mapper
public interface BlemishDetailMapper {

    /**
     * 污点列表查询
     * @param condition 搜索条件
     * @return 污点列表
     */
    List<BlemishDetail> list(@Param("condition") BlemishDetail condition);

    /**
     * 污点详情查询
     * @param blemishDetailId 污点id
     * @return 污点详情
     */
    BlemishDetail detail(@Param("blemishDetailId") Long blemishDetailId);

    /**
     * 保存污点信息
     * @param blemishDetail 污点信息
     * @return 污点id
     */
    Long save(@Param("blemishDetail") BlemishDetail blemishDetail);

    /**
     * 删除污点信息
     * @param blemishDetailId 污点id
     * @return 删除结果
     */
    int delete(@Param("blemishDetailId") Long blemishDetailId);
}
