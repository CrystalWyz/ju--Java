package cn.wyz.murdermystery.mapper;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.User;
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
public interface JuInfoMapper {

    /**
     * 聚--列表查询
     * @param condition 筛选条件
     * @return 聚列表
     */
    List<JuInfo> list(@Param("condition") JuInfo condition);

    /**
     * 聚--详情
     * @param juInfoId 聚id
     * @return 聚详情
     */
    JuInfo detail(@Param("juInfoId") Long juInfoId);

    /**
     * 聚--创建
     * @param juInfo 聚信息
     * @return 聚id
     */
    Long create(@Param("juInfo") JuInfo juInfo);

    /**
     * 聚删除
     * @param juInfoId 聚id
     * @return 删除结果
     */
    int delete(@Param("juInfoId") Long juInfoId);
}
