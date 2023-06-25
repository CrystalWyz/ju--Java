package cn.wyz.serviceverificationcode.service;

/**
 * @author wangnanxiang
 */
public interface NumberCodeService {

    /**
     * 生成随机的数字验证码
     * @param size 验证码长度
     * @return 数字验证码
     */
    Integer generateNumberCode(int size);
}
