package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseException;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.mapper.JuInfoMapper;
import cn.wyz.murdermystery.service.JuInfoService;
import cn.wyz.murdermystery.type.JuInfoStatus;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.UserService;
import cn.wyz.user.type.Gender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public JuInfoDTO add(JuInfoDTO dto) {
        LoginContext context = SecurityContextHolder.getContext();
        dto.setUserId(context.getUserId());
        dto.setStatus(JuInfoStatus.NEW);
        return super.add(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void join(Long juInfoId, Long userId) {
        LOGGER.info("[juInfo#join] user [id: {}] want joint juInfo [id: {}]", userId, juInfoId);
        UserDTO user = userService.get(userId);
        JuInfoDTO juInfo = this.get(juInfoId);
        // TODO 应该使用全局锁
        JuInfoStatus status = juInfo.getStatus();
        if (!status.enableJoin()) {
            throw new BaseException("当前剧本杀的状态不可加入成员");
        }

        Gender gender = user.getGender();
        switch (gender) {
            case MAN -> juInfo.setBoyParticipantNum(juInfo.getBoyParticipantNum() + 1);
            case WOMAN -> juInfo.setGirlParticipantNum(juInfo.getGirlParticipantNum() + 1);
        }
        if (juInfo.full()) {
            juInfo.setStatus(JuInfoStatus.FULL);
        }
        juInfo.getParticipant().add(userId);
        this.update(juInfoId, juInfo);
        // FIXME 是否应该通知发起者
    }

    @Override
    public boolean outGame(Long juInfoId, Long userId, Boolean isForce) {
        LOGGER.info("[juInfo#outGame] user [id: {}] want out juInfo [id: {}]", userId, juInfoId);
        JuInfoDTO juInfo = this.get(juInfoId);
        // TODO 应该使用全局锁
        JuInfoStatus status = juInfo.getStatus();
        if (!status.canOut()) {
            throw new BaseException("当前游戏状态不支持退出");
        }
        if (!juInfo.getParticipant().remove(userId)) {
            throw new BaseException("你已经不在当前游戏中了, 请勿重复操作");
        }
        // TODO
        // TODO
        // TODO
        return false;
    }

    @Override
    public void dismiss(Long juInfoId, Long userId) {

    }

    @Override
    public void startGame(Long juInfoId, Long userId) {

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
}
