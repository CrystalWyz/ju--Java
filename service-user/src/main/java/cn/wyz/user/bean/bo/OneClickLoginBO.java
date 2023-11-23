package cn.wyz.user.bean.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author wangnanxiang
 */
@Data
public class OneClickLoginBO {

    private String phone;

    private String verifyCode;
}
