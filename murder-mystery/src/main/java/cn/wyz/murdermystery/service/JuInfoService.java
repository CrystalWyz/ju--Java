package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface JuInfoService extends MapperService<JuInfo, JuInfoDTO> {


    /**
     * 参加聚本杀
     *
     * @param juInfoId 聚Id
     * @param userId   需要参加的用户
     */
    void applyJoin(Long juInfoId, Long userId);

    /**
     * 参加聚本杀
     *
     * @param juInfoId 聚Id
     * @param userId   需要参加的用户
     */
    void join(Long juInfoId, Long userId);

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
    boolean outGame(Long juInfoId, Long userId, Boolean isForce);

    /**
     * 解散聚本杀
     *
     * @param juInfoId 聚Id
     * @param userId   解散操作人
     */
    void dismiss(Long juInfoId, Long userId);

    /**
     * 开始游戏
     *
     * @param juInfoId 聚Id
     * @param userId   操作人Id
     */
    void startGame(Long juInfoId, Long userId);

    /**
     * 结束游戏
     *
     * @param juInfoId 聚Id
     * @param userId   操作人Id
     */
    void finishGame(Long juInfoId, Long userId);


}
