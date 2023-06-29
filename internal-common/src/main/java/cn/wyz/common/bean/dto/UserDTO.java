package cn.wyz.common.bean.dto;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserDTO", description = "user 传输对象")
public class UserDTO  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Schema(name = "id")
    private Long id;

    @Schema(name = "用户名")
    private String name;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "性别")
    private Integer gender;

    @Schema(name = "身份")
    private Integer identity;

    @Schema(name = "污点")
    private Short blemish;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "区号")
    private String phoneArea;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "修改时间")
    private LocalDateTime updateTime;
}
