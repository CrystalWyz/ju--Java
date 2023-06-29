package cn.wyz.murdermystery.service;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.common.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface JuInfoService {

    /**
     * 聚--创建
     *
     * @param juInfoDTO juInfoDTO
     * @return id
     */
    Long create(JuInfoDTO juInfoDTO);

    /**
     * 聚--分页查询
     * @param pageRequest 分页请求
     * @return 分页数据
     */
    List<JuInfoPageInfo> juInfoPage(PageVM<JuInfoDTO> pageRequest);

    /**
     * 聚--详细信息查询
     * @param juInfoId id
     * @return 聚详情
     */
    JuInfo juInfoDetail(Long juInfoId);

    /**
     * 聚--创建
     * @param juInfo 聚信息
     * @return 聚id
     */
    Long createJuInfo(JuInfo juInfo);

    /**
     * 聚删除
     * @param juInfoId 聚id
     * @return 删除结果
     */
    int deleteJuInfo(Long juInfoId);
}
