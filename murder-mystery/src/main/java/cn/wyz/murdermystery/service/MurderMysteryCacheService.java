package cn.wyz.murdermystery.service;

import java.io.Serializable;

/**
 * 剧本杀信息存储服务
 *
 * @author zhouzhitong
 * @since 2024-03-18
 **/
public interface MurderMysteryCacheService {

    /**
     * 新增剧本杀浏览记录
     *
     * @param gameId 剧本杀Id
     */
    void addReview(Serializable gameId);

    /**
     * 获取剧本杀浏览次数
     *
     * @param gameId 剧本杀Id
     * @return 浏览次数
     */
    Integer getReviewCount(Serializable gameId);

}
