package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseException;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
import cn.wyz.murdermystery.mapper.JuInfoMapper;
import cn.wyz.murdermystery.service.JuInfoService;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import cn.wyz.murdermystery.type.ApplyStatus;
import cn.wyz.murdermystery.type.GameStatus;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.UserService;
import cn.wyz.user.type.Gender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Slf4j
@Service
@AllArgsConstructor
public class JuInfoServiceImpl
        extends MapperServiceImpl<JuInfoMapper, JuInfo, JuInfoDTO>
        implements JuInfoService {

    private final UserService userService;

    private final MurderMysteryApplyService juInfoApplyService;

    @Override
    public JuInfoDTO add(JuInfoDTO dto) {
        LoginContext context = SecurityContextHolder.getContext();
        dto.setUserId(context.getUserId());
        // FIXME 把自己添加进去
        dto.getParticipant().add(context.getUserId());
        dto.setStatus(GameStatus.NEW);
        return super.add(dto);
    }

    @Override
    public void join(JoinGameReq req) {
        Long juInfoId = req.getGameId();
        Long userId = req.getUserId();
        LOGGER.info("[juInfo#join] user [id: {}] want joint juInfo [id: {}]", userId, juInfoId);
        UserDTO user = userService.get(userId);
        JuInfoDTO juInfo = this.get(juInfoId);
        if (juInfo.getParticipant().contains(userId)) {
            throw new BaseException("你已经加入, 请勿重复操作");
        }
        // TODO 应该使用全局锁
        GameStatus status = juInfo.getStatus();
        if (!status.enableJoin()) {
            throw new BaseException("当前剧本杀的状态不可加入成员");
        }
        juInfo.getParticipant().add(userId);
        Gender gender = user.getGender();
        switch (gender) {
            case BOY -> juInfo.setBoyParticipantNum(juInfo.getBoyParticipantNum() + 1);
            case GIRL -> juInfo.setGirlParticipantNum(juInfo.getGirlParticipantNum() + 1);
        }
        if (juInfo.full()) {
            juInfo.setStatus(GameStatus.FULL);
        }
        this.update(juInfoId, juInfo);
        // FIXME 是否应该通知发起者
    }

    @Override
    public boolean outGame(Long juInfoId, Long userId, Boolean isForce) {
        LOGGER.info("[juInfo#outGame] user [id: {}] want out juInfo [id: {}]", userId, juInfoId);
        JuInfoDTO juInfo = this.get(juInfoId);
        // TODO 应该使用全局锁
        GameStatus status = juInfo.getStatus();
        if (!status.canOut()) {
            throw new BaseException("当前游戏状态不支持退出");
        }
        if (!juInfo.getParticipant().remove(userId)) {
            throw new BaseException("你已经不在当前游戏中了, 请勿重复操作");
        }
        UserDTO user = userService.get(userId);
        Gender gender = user.getGender();
        switch (gender) {
            case BOY -> juInfo.setBoyParticipantNum(juInfo.getBoyParticipantNum() - 1);
            case GIRL -> juInfo.setGirlParticipantNum(juInfo.getGirlParticipantNum() - 1);
        }
        if (juInfo.getStatus() == GameStatus.FULL) {
            juInfo.setStatus(GameStatus.NEW);
        }

        update(juInfoId, juInfo);
        return false;
    }

    @Override
    public void dismiss(Long juInfoId, Long userId) {
        LOGGER.info("[juInfo#dismiss] user [id: {}] want dismiss juInfo [id: {}]", userId, juInfoId);
        JuInfoDTO juInfo = this.get(juInfoId);
        // 判断 发起者是否是创建者
        if (!Objects.equals(userId, juInfo.getUserId())) {
            throw new BaseException("你不是创建者, 无法解散");
        }
        // 检查状态
        if (juInfo.getStatus() == GameStatus.DISMISS) {
            throw new BaseException("当前游戏已经解散, 请勿重复操作");
        }
        if (!juInfo.getStatus().canOut()) {
            throw new BaseException("当前游戏状态不支持解散");
        }
        // 开始数据库操作
        juInfo.setStatus(GameStatus.DISMISS);
        update(juInfoId, juInfo);
    }

    @Override
    public void startGame(Long juInfoId, Long userId) {
        LOGGER.info("[juInfo#startGame] user [id: {}] want startGame juInfo [id: {}]", userId, juInfoId);
        JuInfoDTO juInfo = this.get(juInfoId);
        // 判断 发起者是否是创建者
        if (!Objects.equals(userId, juInfo.getUserId())) {
            throw new BaseException("你不是创建者, 无法开始游戏");
        }
        // 检查状态
        if (juInfo.getStatus() != GameStatus.FULL) {
            throw new BaseException("当前游戏状态不支持开始游戏");
        }
        // 开始数据库操作
        juInfo.setStatus(GameStatus.STARTING);
        update(juInfoId, juInfo);
    }

    @Override
    public void finishGame(Long juInfoId, Long userId) {

    }

    @Override
    public JuInfoDTO newDTO() {
        return new JuInfoDTO();
    }

    @Override
    public JuInfo newEntity() {
        return new JuInfo();
    }

    private void addApplyNotice(Long juInfoId, Long userId, String applyReason) {
        MurderMysteryApplyDTO ja = new MurderMysteryApplyDTO();
        ja.setJuInfoId(juInfoId);
        ja.setUserId(userId);
        ja.setApplyReason(applyReason);
        ja.setApplyStatus(ApplyStatus.NEW);
        juInfoApplyService.add(ja);
    }

}
