package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.mapper.BlemishDetailMapper;
import cn.wyz.murdermystery.service.BlemishDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Service
@AllArgsConstructor
@Slf4j
public class BlemishDetailServiceImpl
        extends MapperServiceImpl<BlemishDetailMapper, BlemishDetail, BlemishDetailDTO>
        implements BlemishDetailService {

    @Override
    public BlemishDetailDTO newDTO() {
        return new BlemishDetailDTO();
    }

    @Override
    public BlemishDetail newEntity() {
        return new BlemishDetail();
    }
}
