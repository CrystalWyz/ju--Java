package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.request.BlemishDetailRequest;
import cn.wyz.murdermystery.mapper.BlemishDetailMapper;
import cn.wyz.murdermystery.service.BlemishDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BlemishDetailDTO> getByUserId(Long userId) {
        LOGGER.debug("query blemish detail by userId {}", userId);
        BlemishDetailRequest req = new BlemishDetailRequest();
        req.setUserId(userId);
        return queryAll(req);
    }

}
