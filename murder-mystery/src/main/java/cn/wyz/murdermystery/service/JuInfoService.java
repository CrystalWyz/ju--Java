package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Deprecated
public interface JuInfoService extends MapperService<JuInfo, JuInfoDTO> {


    /**
     * 参加聚本杀
     *
     * @param req 参加剧本杀请求参数
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void join(JoinGameReq req);

    /**
     * 退出游戏
     * <p>
     * TODO 这里可以考虑其他的配置参数
     *
     * @param juInfoId 聚Id
     * @param userId   需要退出的用户
     * @param isForce  是否强制退出
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    boolean outGame(Long juInfoId, Long userId, Boolean isForce);

    /**
     * 解散聚本杀
     *
     * @param juInfoId 聚Id
     * @param userId   解散操作人
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void dismiss(Long juInfoId, Long userId);

    /**
     * 开始游戏
     *
     * @param juInfoId 聚Id
     * @param userId   操作人Id
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void startGame(Long juInfoId, Long userId);

    /**
     * 结束游戏
     *
     * @param juInfoId 聚Id
     * @param userId   操作人Id
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void finishGame(Long juInfoId, Long userId);


}
