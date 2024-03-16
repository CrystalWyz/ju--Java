package cn.wyz.user.service;

import cn.wyz.user.bean.dto.UserDetailDTO;

/**
 * 个人中心服务
 *
 * @author zhouzhitong
 * @since 2024-03-16
 **/
public interface PersonalCenterService {

    /**
     * 获取用户详情, 主要用于用户查看个人信息详情的接口
     *
     * @return
     */
    UserDetailDTO getUserDetail(Long userId);

}
