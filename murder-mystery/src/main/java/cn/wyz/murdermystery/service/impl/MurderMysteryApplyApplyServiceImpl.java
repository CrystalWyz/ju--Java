package cn.wyz.murdermystery.service.impl;

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

import java.time.LocalDateTime;
import java.util.List;

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

        for (MurderMysteryApply mma : mmaList) {
            if (mma.getApplyStatus() == ApplyStatus.NEW) {
                mma.setApplyStatus(ApplyStatus.INVALID);
                mma.setLastModifiedBy(getSystemProvider().getCurrentAuditor());
                mma.setUpdateTime(LocalDateTime.now());
            }
        }
        this.updateBatchById(mmaList);
        return mmaList.size();
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
