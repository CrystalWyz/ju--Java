package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.MurderMysteryApply;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface MurderMysteryApplyService
        extends MapperService<MurderMysteryApply, MurderMysteryApplyDTO> {

    /**
     * 作废所有申请
     *
     * @param gameId 剧本杀id {@link cn.wyz.murdermystery.bean.MurderMystery#getId()}
     * @return
     */
    int invalidAll(Long gameId);

}
