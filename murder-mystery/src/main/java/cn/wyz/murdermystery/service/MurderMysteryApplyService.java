package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.MurderMysteryApply;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 剧本杀申请 -- 服务类
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

    /**
     * 作废申请
     *
     * @param id     申请id
     * @param reason 作废原因
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    void invalid(Long id, String reason);

}
