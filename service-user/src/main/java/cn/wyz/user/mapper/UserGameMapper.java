package cn.wyz.user.mapper;

import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.user.bean.UserGame;
import org.apache.ibatis.annotations.Mapper;

/**
 * user game Mapper 接口
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Mapper
public interface UserGameMapper extends CrudMapper<UserGame> {

}
