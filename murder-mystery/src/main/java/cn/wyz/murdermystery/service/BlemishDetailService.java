package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface BlemishDetailService
        extends MapperService<BlemishDetail, BlemishDetailDTO> {

    /**
     * 根据
     *
     * @param userId
     * @return
     */
    List<BlemishDetailDTO> getByUserId(Long userId);


    @Override
    default BlemishDetailDTO newDTO() {
        return new BlemishDetailDTO();
    }

    @Override
    default BlemishDetail newEntity() {
        return new BlemishDetail();
    }

}
