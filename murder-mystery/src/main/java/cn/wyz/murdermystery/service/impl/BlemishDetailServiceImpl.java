package cn.wyz.murdermystery.service.impl;

import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.common.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.BlemishDetailPageInfo;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.mapper.BlemishDetailMapper;
import cn.wyz.murdermystery.service.BlemishDetailService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Service
public class BlemishDetailServiceImpl implements BlemishDetailService {

    private final BeanConvert beanConvert;

    private final BlemishDetailMapper blemishDetailMapper;

    public BlemishDetailServiceImpl(BeanConvert beanConvert, BlemishDetailMapper blemishDetailMapper) {
        this.beanConvert = beanConvert;
        this.blemishDetailMapper = blemishDetailMapper;
    }

    @Override
    public List<BlemishDetailPageInfo> blemishDetailPage(PageVM<BlemishDetailDTO> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        BlemishDetail condition = beanConvert.blemishDetailDTOToBlemishDetail(pageRequest.getCondition());
        List<BlemishDetail> blemishDetailList = blemishDetailMapper.list(condition);
        if (ObjectUtils.isNotEmpty(blemishDetailList)) {
            return blemishDetailList.stream().map(beanConvert::blemishDetailToBlemishdetailPageInfo).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public BlemishDetail blemishDetailDetail(Long blemishDetailId) {
        return blemishDetailMapper.detail(blemishDetailId);
    }

    @Override
    public Long createBlemishDetail(BlemishDetail blemishDetail) {

        // 属性填充
        blemishDetail.setCreateTime(LocalDateTime.now());

        return blemishDetailMapper.save(blemishDetail);
    }

    @Override
    public int deleteBlemishDetail(Long blemishDetailId) {
        return blemishDetailMapper.delete(blemishDetailId);
    }
}
