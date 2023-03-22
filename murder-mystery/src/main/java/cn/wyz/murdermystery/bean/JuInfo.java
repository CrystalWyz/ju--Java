package cn.wyz.murdermystery.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Getter
@Setter
@TableName("ju_info")
@ApiModel(value = "JuInfo对象", description = "")
public class JuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("	聚主题")
    @TableField("title")
    private String title;

    @ApiModelProperty("参与用户")
    @TableField("users")
    private Object users;

    @ApiModelProperty("	预计开始时间")
    @TableField("begin")
    private LocalDateTime begin;

    @ApiModelProperty("预计结束时间")
    @TableField("finish")
    private LocalDateTime finish;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("聚描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建者id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
