package cn.wyz.user.mapper;

import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.user.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Mapper
public interface UserMapper extends CrudMapper<User> {

}
