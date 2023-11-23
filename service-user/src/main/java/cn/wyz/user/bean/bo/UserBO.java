package cn.wyz.user.bean.bo;

import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.user.constant.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserBO extends BaseDTO {

    private String name;

    private String nickName;

    private Gender gender;

    private Integer identity;

    private String username;

    private String password;

    private Boolean enabled;

    private Integer blemish;

    private String phone;

    private String phoneArea;

    private Boolean isSupperAdmin = false;
}
