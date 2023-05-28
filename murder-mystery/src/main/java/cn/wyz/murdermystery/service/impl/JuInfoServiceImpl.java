package cn.wyz.murdermystery.service.impl;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.exception.AppException;
import cn.wyz.murdermystery.service.JuInfoService;
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
@Slf4j
@Service
public class JuInfoServiceImpl implements JuInfoService {

    private final BeanConvert beanConvert;

    public JuInfoServiceImpl(BeanConvert beanConvert) {
        this.beanConvert = beanConvert;
    }

    @Override
    public Long create(JuInfoDTO juInfoDTO) {
        return null;
    }
}
