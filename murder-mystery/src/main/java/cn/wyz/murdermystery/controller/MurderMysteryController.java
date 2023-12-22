package cn.wyz.murdermystery.controller;

import cn.wyz.common.anno.Idempotence;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.mapper.controller.BaseController;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.service.MurderMysteryService;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author wnx
 */
@RestController
@RequestMapping("/api/v1/murderMysteries")
@Tag(name = "剧本杀接口", description = "剧本杀接口")
public class MurderMysteryController
        extends BaseController<MurderMystery, MurderMysteryDTO,
        MurderMysteryRequest, MurderMysteryService> {


    /**
     * 加入聚
     *
     * @param gameId 聚id
     * @return ResponseResult<Void>
     */
    @Idempotence
    @PatchMapping("/join/{gameId}")
    public ResponseResult<Void> join(@PathVariable("gameId") Long gameId) {
        service().join(gameId);
        return ResponseResult.success();
    }

    /**
     * 处理申请
     *
     * @param req 处理申请请求参数
     * @return ResponseResult<Void>
     */
    @PatchMapping("/handleApply")
    @Idempotence
    public ResponseResult<Void> handleApply(@RequestBody HandleApplyGameReq req) {
        service().handleApply(req);
        return ResponseResult.success();
    }


    /**
     * 退出游戏
     *
     * @param juInfoId 聚id
     * @param isForce  是否强制退出
     */
    @Idempotence
    @PatchMapping("/outGame/{juInfoId}")
    public ResponseResult<Void> outGame(@PathVariable("juInfoId") Long juInfoId,
                                        @RequestParam(value = "isForce", required = false) Boolean isForce) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().outGame(juInfoId, userId, isForce);
        return ResponseResult.success();
    }

    /**
     * 准备游戏
     *
     * @param juInfoId 聚id
     */
    @Idempotence
    @PatchMapping("/prepare/{juInfoId}")
    public ResponseResult<Void> prepare(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().prepareGame(juInfoId, userId);
        return ResponseResult.success();
    }

    /**
     * 签到
     *
     * @param juInfoId 聚id
     */
    @Idempotence
    @PatchMapping("/sign/{juInfoId}")
    public ResponseResult<Void> sign(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().startSignGame(juInfoId, userId);
        return ResponseResult.success();
    }


    /**
     * 开始游戏
     *
     * @param juInfoId 聚id
     */
    @Idempotence
    @PatchMapping("/start/{juInfoId}")
    public ResponseResult<Void> start(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().startGame(juInfoId, userId);
        return ResponseResult.success();
    }

    /**
     * 完成游戏
     *
     * @param juInfoId 聚id
     */
    @Idempotence
    @PatchMapping("/finish/{juInfoId}")
    public ResponseResult<Void> finish(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().finishGame(juInfoId, userId);
        return ResponseResult.success();
    }

    /**
     * 解散游戏
     *
     * @param juInfoId 聚id
     */
    @Idempotence
    @PatchMapping("/dismiss/{juInfoId}")
    public ResponseResult<Void> dismiss(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().dismiss(juInfoId, userId);
        return ResponseResult.success();
    }

}
