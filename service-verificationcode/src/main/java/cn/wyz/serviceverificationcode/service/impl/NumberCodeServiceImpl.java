package cn.wyz.serviceverificationcode.service.impl;

import cn.wyz.serviceverificationcode.service.NumberCodeService;
import org.springframework.stereotype.Service;

/**
 * @author wangnanxiang
 */
@Service
public class NumberCodeServiceImpl implements NumberCodeService {
    @Override
    public Integer generateNumberCode(int size) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10,size - 1));
    }
}
