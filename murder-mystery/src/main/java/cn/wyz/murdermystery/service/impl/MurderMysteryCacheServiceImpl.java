package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.utils.MybatisPlusWrapperUtils;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.MurderMysteryCacheService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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

    /**
     * 缓存, key: 剧本杀浏览次数
     */
    private static final String REVIEW_COUNT_KEY = "murder_mystery:review_count";

    @Override
    public void addReview(Serializable gameId, Long userId) {
        LOGGER.debug("MurderMystery {} add review count +1 by userId {}", gameId, userId);
        redisTemplate.opsForHash().increment(REVIEW_COUNT_KEY, String.valueOf(gameId), 1);
    }

    @Override
    public Integer getReviewCount(Serializable gameId) {
        HashOperations<String, String, Integer> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(REVIEW_COUNT_KEY, String.valueOf(gameId));
    }

    /**
     * 定时任务: 每小时执行一次, 将缓存的数据刷新到数据库中
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleReview() {
        LOGGER.info("starting review count to DB...");
        HashOperations<String, String, Integer> hashOperations = redisTemplate.opsForHash();
        long count = murderMysteryMapper.selectCount(MybatisPlusWrapperUtils.simpleQuery());
        int limit = 1000;
        int i = 0;
        while (count >= ((long) limit * i)) {
            MurderMysteryRequest req = new MurderMysteryRequest();
            req.setPage(i);
            req.setSize(limit);

            murderMysteryMapper.selectPage(new Page<>(i, limit, false), MybatisPlusWrapperUtils.simpleQuery(req)).getRecords().forEach(murderMystery -> {
                Integer reviewCount = hashOperations.get(REVIEW_COUNT_KEY, String.valueOf(murderMystery.getId()));
                if (reviewCount != null && !reviewCount.equals(murderMystery.getReviews())) {
                    LOGGER.debug("update review count to DB, gameId: {}, reviewCount: {}", murderMystery.getId(), reviewCount);
                    murderMystery.setReviews(reviewCount);
                    murderMysteryMapper.updateById(murderMystery);
                }
            });
            i++;
        }
        LOGGER.info("finish review count to DB...");
    }

}
