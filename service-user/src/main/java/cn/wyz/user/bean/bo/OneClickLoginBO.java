package cn.wyz.user.bean.bo;

import lombok.Data;

/**
 * @author wangnanxiang
 */
@Data
public class OneClickLoginBO {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String verifyCode;
}
