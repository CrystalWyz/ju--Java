package cn.wyz.murdermystery.mapper;

import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.user.context.LoginContext;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wyz
 */
@Mapper
public interface MurderMysteryMapper extends CrudMapper<MurderMystery> {

    List<MurderMystery> list(MurderMystery condition);

    MurderMystery getUserConflictJoined(@Param("userInfo") LoginContext userInfo, @Param("startTime") LocalDateTime startTime);
}
