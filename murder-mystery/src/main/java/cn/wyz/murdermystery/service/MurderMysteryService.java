package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyz
 */
public interface MurderMysteryService extends MapperService<MurderMystery, MurderMysteryDTO> {

    /**
     * 参加聚本杀
     *
     * @param req 参加剧本杀请求参数
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void join(JoinGameReq req);

    /**
     * 撤销申请
     *
     * @param req 撤销申请请求参数
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void cancelApply(JoinGameReq req);

    /**
     * 处理申请
     *
     * @param req 申请加入剧本杀请求参数
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void handleApply(HandleApplyGameReq req);

    /**
     * 退出游戏
     * <p>
     * TODO 这里可以考虑其他的配置参数
     *
     * @param id      聚Id
     * @param userId  需要退出的用户
     * @param isForce 是否强制退出
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    boolean outGame(Long id, Long userId, Boolean isForce);

    /**
     * 解散聚本杀
     *
     * @param id     聚Id
     * @param userId 解散操作人
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void dismiss(Long id, Long userId);

    /**
     * 准备游戏
     *
     * @param id     聚Id
     * @param userId 操作人Id
     */
    void prepareGame(Long id, Long userId);

    /**
     * 开始签到
     *
     * @param id     剧本杀Id
     * @param userId 操作人Id
     */
    void signGame(Long id, Long userId);

    /**
     * 开始游戏
     *
     * @param id     聚Id
     * @param userId 操作人Id
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void startGame(Long id, Long userId);

    /**
     * 结束游戏
     *
     * @param id     聚Id
     * @param userId 操作人Id
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void finishGame(Long id, Long userId);


}
