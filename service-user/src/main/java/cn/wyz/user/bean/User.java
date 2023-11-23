package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.user.type.Gender;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

/**
 * User对象
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ju_user", resultMap = "JuUserMap")
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
     *
     * @see Gender 性别
     */
    private Gender gender;

    /**
     * 身份
     */
    private Integer identity;

    /**
     * 污点
     */
    private Integer blemish;

    /**
     * 账户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 是否启用, 默认可用
     */
    @TableField(value = "enabled", jdbcType = JdbcType.BOOLEAN)
    private Boolean enabled = true;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 区号
     */
    private String phoneArea;


}
