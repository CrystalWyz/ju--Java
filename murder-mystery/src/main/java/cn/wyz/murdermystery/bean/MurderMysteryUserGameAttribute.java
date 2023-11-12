package cn.wyz.murdermystery.bean;

import cn.wyz.mapper.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wyz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class MurderMysteryUserGameAttribute extends BaseEntity {

    private Long userId;

    private Integer participateNum;

    private Integer quitMidwayNum;

    private Integer level;

    private List<String> tags;

}
