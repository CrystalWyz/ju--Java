package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.mapper.controller.BaseController;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.request.JuInfoJoinGameReq;
import cn.wyz.murdermystery.bean.request.JuInfoRequest;
import cn.wyz.murdermystery.service.JuInfoService;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@RestController
@RequestMapping("/api/v1/murderMystery/juInfos")
@Tag(name = "聚接口样例", description = "聚接口样例")
public class JuInfoController
        extends BaseController<JuInfo, JuInfoDTO, JuInfoRequest, JuInfoService> {

    /**
     * 加入聚
     *
     * @param juInfoId 聚id
     * @return ResponseResult<Void>
     */
    @PatchMapping("/join/{juInfoId}")
    public ResponseResult<Void> join(@PathVariable("juInfoId") Long juInfoId,
                                     @RequestBody JuInfoJoinGameReq req) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        req.setJuInfoId(juInfoId);
        req.setUserId(userId);
        service().join(req);
        return ResponseResult.success();
    }

    /**
     * 退出游戏
     *
     * @param juInfoId 聚id
     * @param isForce  是否强制退出
     */
    @PatchMapping("/outGame/{juInfoId}")
    public ResponseResult<Void> outGame(@PathVariable("juInfoId") Long juInfoId,
                                        @RequestParam(value = "isForce", required = false) Boolean isForce) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().outGame(juInfoId, userId, isForce);
        return ResponseResult.success();
    }

    /**
     * 开始游戏
     *
     * @param juInfoId 聚id
     */
    @PatchMapping("/start/{juInfoId}")
    public ResponseResult<Void> start(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().startGame(juInfoId, userId);
        return ResponseResult.success();
    }

    /**
     * 结束游戏
     *
     * @param juInfoId 聚id
     */
    @PatchMapping("/finish/{juInfoId}")
    public ResponseResult<Void> finish(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().finishGame(juInfoId, userId);
        return ResponseResult.success();
    }

    /**
     * 结束游戏
     *
     * @param juInfoId 聚id
     */
    @PatchMapping("/dismiss/{juInfoId}")
    public ResponseResult<Void> dismiss(@PathVariable("juInfoId") Long juInfoId) {
        LoginContext context = SecurityContextHolder.getContext();
        Long userId = context.getUserId();
        service().dismiss(juInfoId, userId);
        return ResponseResult.success();
    }
}
