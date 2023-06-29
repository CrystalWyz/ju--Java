package cn.wyz.murdermystery.service.impl;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.common.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.mapper.JuInfoMapper;
import cn.wyz.murdermystery.service.JuInfoService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class JuInfoServiceImpl implements JuInfoService {

    private final BeanConvert beanConvert;

    private final JuInfoMapper juInfoMapper;

    public JuInfoServiceImpl(BeanConvert beanConvert, JuInfoMapper juInfoMapper) {
        this.beanConvert = beanConvert;
        this.juInfoMapper = juInfoMapper;
    }

    @Override
    public Long create(JuInfoDTO juInfoDTO) {
        return null;
    }

    @Override
    public List<JuInfoPageInfo> juInfoPage(PageVM<JuInfoDTO> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        JuInfo condition = beanConvert.juInfoDTOToJuInfo(pageRequest.getCondition());
        List<JuInfo> juInfoList = juInfoMapper.list(condition);
        if (ObjectUtils.isNotEmpty(juInfoList)) {
            return juInfoList.stream().map(beanConvert::juInfoTOJuInfoPageInfo).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public JuInfo juInfoDetail(Long juInfoId) {
        return juInfoMapper.detail(juInfoId);
    }

    @Override
    public Long createJuInfo(JuInfo juInfo) {

        // 属性填充
        LocalDateTime now = LocalDateTime.now();
        juInfo.setCreateTime(now);
        juInfo.setUpdateTime(now);

        return juInfoMapper.save(juInfo);
    }

    @Override
    public int deleteJuInfo(Long juInfoId) {

        return juInfoMapper.delete(juInfoId);
    }
}
