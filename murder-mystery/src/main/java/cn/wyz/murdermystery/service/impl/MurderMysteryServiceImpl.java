package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.bo.MurderMysteryBO;
import cn.wyz.murdermystery.bean.request.MurderMysteryPageRequest;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.bean.response.MurderMysteryPageResponse;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.MurderMysteryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wyz
 */
@Service
public class MurderMysteryServiceImpl implements MurderMysteryService {

    private final BeanConvert beanConvert;

    private final MurderMysteryMapper murderMysteryMapper;

    public MurderMysteryServiceImpl(BeanConvert beanConvert, MurderMysteryMapper murderMysteryMapper) {
        this.beanConvert = beanConvert;
        this.murderMysteryMapper = murderMysteryMapper;
    }

    @Override
    public PageInfo<MurderMysteryBO> murderMysteryPage(PageVM<MurderMysteryPageRequest> pageRequest) {

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize(), false);
        MurderMystery condition = beanConvert.MurderMysteryPageRequestToMurderMystery(pageRequest.getCondition());
        List<MurderMystery> murderMysteryList = murderMysteryMapper.list(condition);
        PageInfo<MurderMystery> page = new PageInfo<>(murderMysteryList);
        PageInfo<MurderMysteryBO> boPage = beanConvert.murderMysteryPageToMurderMysteryBOPage(page);
        if (ObjectUtils.isNotEmpty(murderMysteryList)) {
            boPage.setList(murderMysteryList.stream().map(beanConvert::murderMysteryToMurderMysteryBO).collect(Collectors.toList()));
        }
        return boPage;
    }

    @Override
    public Long murderMysteryCreate(MurderMysteryBO murderMysteryBO) {

        MurderMystery murderMystery = beanConvert.murderMysteryBOToMurderMystery(murderMysteryBO);

        // 必要属性补充
        LocalDateTime now = LocalDateTime.now();
        murderMystery.setCreateTime(now);
        murderMystery.setUpdateTime(now);
        murderMysteryMapper.insert(murderMystery);
        return murderMystery.getId();
    }
}
