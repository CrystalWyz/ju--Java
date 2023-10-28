package cn.wyz.user.service;

import cn.wyz.user.dto.LoginDTO;
import cn.wyz.user.vo.UserRoleVO;
import cn.wyz.user.vo.UserTokenVO;

/**
 * 登录服务
 *
 * @author zhouzhitong
 * @since 2023/5/21
 */
public interface AuthorityService {

    /**
     * 登录
     *
     * @param param 登录参数
     * @return 登录结果
     */
    UserTokenVO login(LoginDTO param);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    UserRoleVO getCurrentUserInfo();

}
