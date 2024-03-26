package cn.wyz.user.bean;

import cn.wyz.mapper.bean.dto.BaseDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户个人资料表
 *
 * @author zhouzhitong
 * @since 2024-03-20
 **/
@Data
@TableName(value = "user_profile", resultMap = "UserProfileMap")
public class UserProfile extends BaseDTO {

    /**
     * 关联用户ID
     *
     * @see User#getId()
     */
    private Long userId;

    /**
     * 用户真实姓名
     */
    private String fullName;

    /**
     * 用户个人简介或签名
     */
    private String bio;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 微信号
     */
    private String wxCode;

    /**
     * 身份
     */
    private Integer identity;

}
