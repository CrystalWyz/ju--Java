package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseRuntimeException;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.MurderMysteryApply;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.request.MurderMysteryApplyReq;
import cn.wyz.murdermystery.mapper.MurderMysteryApplyMapper;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import cn.wyz.murdermystery.type.ApplyStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 剧本杀申请表 服务实现类
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Service
@AllArgsConstructor
@Slf4j
public class MurderMysteryApplyApplyServiceImpl
        extends MapperServiceImpl<MurderMysteryApplyMapper, MurderMysteryApply, MurderMysteryApplyDTO>
        implements MurderMysteryApplyService {

    @Override
    public int invalidAll(Long gameId) {
        LOGGER.debug("invalid all MurderMysteryApply, id: {}", gameId);
        MurderMysteryApplyReq req = new MurderMysteryApplyReq();
        req.setGameId(gameId);
        QueryWrapper<MurderMysteryApply> wrapper = this.buildQuery(req);
        List<MurderMysteryApply> mmaList = this.list(wrapper);
        if (CollectionUtils.isEmpty(mmaList)) {
            return 0;
        }

        for (MurderMysteryApply mma : mmaList) {
            if (mma.getApplyStatus() == ApplyStatus.NEW) {
                mma.setApplyStatus(ApplyStatus.INVALID);
                mma.setLastModifiedBy(systemProvider.getCurrentUserId());
                mma.setUpdateTime(LocalDateTime.now());
            }
        }
        this.updateBatchById(mmaList);
        return mmaList.size();
    }

    @Override
    public void invalid(Long id, String reason) {
        LOGGER.info("invalid MurderMysteryApply, id: {}", id);
        MurderMysteryApplyDTO mma = get(id);
        LOGGER.debug("MurderMysteryApply: {}", mma);
        if (mma.getApplyStatus() != ApplyStatus.NEW) {
            // 过滤掉
            return;
        }
        mma.setApplyStatus(ApplyStatus.INVALID);
        mma.setRejectReason(reason);
        this.update(mma);
    }

    @Override
    public void cancelApply(Long id) {
        LOGGER.info("cancelApply MurderMysteryApply, id: {}", id);
        MurderMysteryApplyDTO mma = this.get(id);
        if (mma == null || mma.getApplyStatus() != ApplyStatus.NEW) {
            return;
        }
        Long currentUserId = systemProvider.getCurrentUserId();
        if (!Objects.equals(mma.getCreatedBy(), currentUserId)) {
            throw new BaseRuntimeException("不能撤销他人的申请");
        }
        mma.setApplyStatus(ApplyStatus.CANCEL);
        mma.setRejectReason("主动撤销申请");
        this.update(mma);
    }

    @Override
    public MurderMysteryApplyDTO newDTO() {
        return new MurderMysteryApplyDTO();
    }

    @Override
    public MurderMysteryApply newEntity() {
        return new MurderMysteryApply();
    }
}
