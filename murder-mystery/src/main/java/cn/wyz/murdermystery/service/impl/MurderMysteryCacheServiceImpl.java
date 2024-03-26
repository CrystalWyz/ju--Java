package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.utils.MybatisPlusWrapperUtils;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.MurderMysteryCacheService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhitong
 * @since 2024-03-18
 **/
@Service
@Slf4j
@AllArgsConstructor
public class MurderMysteryCacheServiceImpl implements MurderMysteryCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final MurderMysteryMapper murderMysteryMapper;

    private static final String REVIEW_COUNT_VIEW_KEY = "review_count:view";

    @Override
    public void addReview(Serializable gameId) {
        murderMysteryMapper.addReview(gameId);
    }

    @Override
    public Integer getReviewCount(Serializable gameId) {
        Integer reviews = (Integer) redisTemplate.opsForValue().get(REVIEW_COUNT_VIEW_KEY + gameId);
        if (ObjectUtils.isEmpty(reviews)) {
            reviews = murderMysteryMapper.selectById(gameId).getReviews();
            redisTemplate.opsForValue().set(REVIEW_COUNT_VIEW_KEY + gameId, reviews, 5, TimeUnit.MINUTES);
        }
        return reviews;
    }
}
