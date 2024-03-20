package cn.wyz.user.dto;


import cn.wyz.mapper.bean.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (Group)DTO
 *
 * @author zhouzhitong
 * @since 2024-03-24 22:05:29
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GroupDTO extends BaseDTO {

    private String name;
    private String description;
    private String avatar;
}
