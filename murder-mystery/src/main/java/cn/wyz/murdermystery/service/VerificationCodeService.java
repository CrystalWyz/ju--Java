package cn.wyz.murdermystery.service;

import cn.wyz.common.bean.response.TokenResponseDTO;

/**
 * @author wangnanxiang
 */
public interface VerificationCodeService {

    /**
     * 获取验证码
     * @param passengerPhone 乘客手机号
     */
    void generatorVerificationCode(String passengerPhone);

    /**
     * 校验验证码
     * @param passengerPhone 乘客手机号
     * @param verificationCode 验证码
     * @return token
     */
    TokenResponseDTO checkVerificationCode(String passengerPhone, String verificationCode);
}
