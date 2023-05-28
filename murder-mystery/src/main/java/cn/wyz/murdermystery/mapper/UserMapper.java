package cn.wyz.murdermystery.mapper;

import cn.wyz.murdermystery.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户信息
     * @param user 新用户信息
     * @return 用户id
     */
    Long create(@Param("user") User user);
}
