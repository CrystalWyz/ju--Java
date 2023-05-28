package cn.wyz.murdermystery.service;

import cn.wyz.murdermystery.bean.User;
import cn.wyz.murdermystery.bean.dto.UserDTO;
import cn.wyz.murdermystery.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.UserPageInfo;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface UserService {

    /**
     * 创建用户
     * @param userDTO 新用户信息
     * @return 用户id
     */
    Long createUser(UserDTO userDTO);

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 用户详情
     */
    User userDetail(Long userId);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void deleteUser(Long userId);

    /**
     * 用户分页查询
     * @param pageRequest 分页请求
     */
    List<UserPageInfo> userPage(PageVM<UserDTO> pageRequest);
}
