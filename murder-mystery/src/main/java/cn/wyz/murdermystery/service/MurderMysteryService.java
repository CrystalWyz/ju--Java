package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bo.MurderMysteryJoinBO;
import cn.wyz.user.context.LoginContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyz
 */
public interface MurderMysteryService extends MapperService<MurderMystery, MurderMysteryDTO> {

    /**
     * 参加聚本杀: 包括申请加入和直接加入
     *
     * @param gameId 参加剧本杀请求参数
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    void join(Long gameId);

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
     * 房主开启签到, 如果是成员, 则签到
     *
     * @param id     剧本杀Id
     * @param userId 操作人Id
     */
    void startSignGame(Long id, Long userId);

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

    MurderMysteryJoinBO canJoin(LoginContext context, Long gameId);

    /**
     * 尝试获取用户时间冲突的剧本杀游戏
     *
     * @param userId 用户Id
     * @param gameId 游戏Id
     * @return 如果返回 null, 说明可以加入, 如果返回不为 null, 说明不能加入, 返回的是不能加入的原因
     */
    @Deprecated
    MurderMysteryDTO tryGetConflictGame(Long userId, Long gameId);

    @Override
    default MurderMysteryDTO newDTO() {
        return new MurderMysteryDTO();
    }

    @Override
    default MurderMystery newEntity() {
        return new MurderMystery();
    }

}
