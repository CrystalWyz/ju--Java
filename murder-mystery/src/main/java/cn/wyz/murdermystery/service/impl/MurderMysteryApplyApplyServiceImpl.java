package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.MurderMysteryApply;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.mapper.MurderMysteryApplyMapper;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public MurderMysteryApplyDTO newDTO() {
        return new MurderMysteryApplyDTO();
    }

    @Override
    public MurderMysteryApply newEntity() {
        return new MurderMysteryApply();
    }
}
