package cn.wyz.murdermystery.mapper;

import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.MurderMystery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wyz
 */
@Mapper
public interface MurderMysteryMapper {

    List<MurderMystery> list(MurderMystery condition);
}
