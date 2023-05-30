package cn.wyz.murdermystery.bean.dto;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wnx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "user 传输对象")
public class UserDTO  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("身份")
    private Integer identity;

    @ApiModelProperty("污点")
    private Short blemish;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("区号")
    private String phoneArea;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
}
