package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.JuInfoApply;
import cn.wyz.murdermystery.bean.dto.JuInfoApplyDTO;
import cn.wyz.murdermystery.mapper.JuInfoApplyMapper;
import cn.wyz.murdermystery.service.JuInfoApplyService;
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
public class JuInfoApplyServiceImpl
        extends MapperServiceImpl<JuInfoApplyMapper, JuInfoApply, JuInfoApplyDTO>
        implements JuInfoApplyService {

    @Override
    public JuInfoApplyDTO newDTO() {
        return new JuInfoApplyDTO();
    }

    @Override
    public JuInfoApply newEntity() {
        return new JuInfoApply();
    }
}
