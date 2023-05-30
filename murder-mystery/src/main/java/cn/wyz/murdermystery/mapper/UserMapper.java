package cn.wyz.murdermystery.mapper;

import cn.wyz.murdermystery.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     *
     * @param user 新用户信息
     * @return 用户id
     */
    Long save(@Param("user") User user);

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User detail(@Param("userId") Long userId);

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    void delete(@Param("userId") Long userId);

    /**
     * 用户列表查询
     *
     * @param condition 搜索条件
     */
    List<User> list(@Param("condition") User condition);
}
