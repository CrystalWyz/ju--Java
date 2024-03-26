package cn.wyz.user.dto;

import cn.wyz.common.util.BeanUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouzhitong
 * @since 2024-03-16
 **/
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserDetailDTO extends UserDTO {

    /**
     * 用户详情
     * <p>
     * key: 详情类型
     * value: 用户具体详情
     */
    @Schema(name = "用户详情")
    private Map<String, Object> detailMap;

    public void setUser(UserDTO user) {
        BeanUtils.copy(user, this);
    }

    public void addUserDetail(String key, Object val) {
        if (detailMap == null) {
            detailMap = new HashMap<>();
        }
        detailMap.put(key, val);
    }

}
