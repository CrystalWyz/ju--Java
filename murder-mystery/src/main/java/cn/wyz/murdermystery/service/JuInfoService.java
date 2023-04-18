package cn.wyz.murdermystery.service;

import cn.wyz.murdermystery.bean.dto.JuInfoDTO;

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
}
