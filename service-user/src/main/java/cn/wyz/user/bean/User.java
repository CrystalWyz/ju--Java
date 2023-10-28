package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * User对象
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Data
@Schema(name = "User对象")
@TableName("ju_user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 身份
     */
    private Integer identity;

    /**
     * 污点
     */
    private Integer blemish;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 区号
     */
    private String phoneArea;

}
