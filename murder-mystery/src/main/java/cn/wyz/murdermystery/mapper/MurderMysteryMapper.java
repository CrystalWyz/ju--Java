package cn.wyz.murdermystery.mapper;

import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.murdermystery.bean.MurderMystery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wyz
 */
@Mapper
public interface MurderMysteryMapper extends CrudMapper<MurderMystery> {

    List<MurderMystery> list(MurderMystery condition);

}
