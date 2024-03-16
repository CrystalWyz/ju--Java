package cn.wyz.user.handler;

import cn.hutool.core.lang.Pair;

/**
 * @author zhouzhitong
 * @since 2024-03-16
 **/
public interface UserDetailHandler {

    Pair<String, Object> getUserDetail(Long userId);

}
