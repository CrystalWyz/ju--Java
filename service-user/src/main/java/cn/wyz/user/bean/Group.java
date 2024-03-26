package cn.wyz.user.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 群组表
 *
 * @author zhouzhitong
 * @since 2024-03-20
 **/
@Data
@TableName(value = "\"group\"", resultMap = "GroupMap")
public class Group extends BaseEntity {

    /**
     * 群组名
     */
    private String name;

    /**
     * 群组描述，简要介绍群组的目的和内容。
     */
    private String description;

    /**
     * 群组头像，存储头像的URL或文件路径。
     */
    private String avatar;

}
