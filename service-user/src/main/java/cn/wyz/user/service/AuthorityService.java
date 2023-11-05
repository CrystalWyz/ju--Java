package cn.wyz.user.service;

import cn.wyz.common.bean.response.TokenResponseDTO;
import cn.wyz.user.dto.LoginDTO;
import cn.wyz.user.vo.UserInfoVO;
import cn.wyz.user.vo.UserTokenVO;

/**
 * 登录服务: 登录、刷新token、退出登录
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
     * 刷新token
     *
     * @return new accessToken
     */
    TokenResponseDTO refreshToken();

    /**
     * 退出登录
     */
    void logout(String token);

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    UserInfoVO getCurrentUserInfo();

}
