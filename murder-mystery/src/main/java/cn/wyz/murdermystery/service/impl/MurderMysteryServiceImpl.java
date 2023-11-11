package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseException;
import cn.wyz.common.exception.RefreshException;
import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.mapper.vo.PageResultVO;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.BlemishDetailService;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import cn.wyz.murdermystery.service.MurderMysteryService;
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
 * @author wyz
 */
@Service
@Slf4j
@AllArgsConstructor
public class MurderMysteryServiceImpl extends MapperServiceImpl<MurderMysteryMapper, MurderMystery, MurderMysteryDTO> implements MurderMysteryService {

    private final BeanConvert beanConvert;

    private final MurderMysteryApplyService juInfoApplyService;

    private final UserService userService;

    private final BlemishDetailService blemishDetailService;

    @Override
    public <Query extends BaseRequest> PageResultVO<MurderMysteryDTO> page(Query query) {
        MurderMysteryRequest req = (MurderMysteryRequest) query;
        PageResultVO<MurderMysteryDTO> page = super.page(query);


        return page;
    }

    @Override
    public MurderMysteryDTO add(MurderMysteryDTO dto) {
        LoginContext context = SecurityContextHolder.getContext();
        dto.setUserId(context.getUserId());
        tryAddPerson(dto, context.getUserId(), context.getGender());
        dto.setStatus(GameStatus.NEW);
        return super.add(dto);
    }

    @Override
    public void join(JoinGameReq req) {
        Long gameId = req.getGameId();
        Long userId = req.getUserId();
        LOGGER.info("[murderMystery#join] user [id: {}] want joint game [id: {}]", userId, gameId);
        UserDTO user = userService.get(userId);
        MurderMysteryDTO mm = this.get(gameId);
        if (mm.getStatus() != GameStatus.NEW) {
            throw new BaseException("当前游戏状态, 无法加入");
        }
        if (mm.needApply()) {
            addApplyNotice(mm, userId, req.getReason());
        } else {
            if (tryAddPerson(mm, userId, user.getGender())) {
                throw new BaseException("你已经加入, 请勿重复操作");
            }
            if (isFull(mm)) {
                mm.setStatus(GameStatus.FULL);
            }
        }
        this.update(gameId, mm);
    }

    @Override
    public void handleApply(HandleApplyGameReq req) {
        LOGGER.info("[murderMystery#handleApply] req {}", req);

    }

    @Override
    public void cancelApply(JoinGameReq req) {

    }

    @Override
    public boolean outGame(Long id, Long userId, Boolean isForce) {
        LOGGER.info("[murderMystery#outGame] user [id: {}] want out game [id: {}, isForce: {}] ",
                userId, id, isForce);
        MurderMysteryDTO mm = this.get(id);
        GameStatus status = mm.getStatus();

        if (!status.canOut() && !isForce) {
            throw new BaseException("当前游戏状态不支持退出");
        }
        // 如果是在无法退出游戏状态时, 则记录有瑕疵
        boolean hasBlemish = !status.canOut();
        UserDTO user = userService.get(userId);
        if (tryRemovePerson(mm, userId, user.getGender())) {
            throw new BaseException("你已不在游戏中, 无法退出");
        }
        if (hasBlemish) {
            addBlemish(id, userId, "在无法退出游戏状态时, 退出游戏");
        }
        // FIXME 是否修改状体
        if (status == GameStatus.FULL) {
            mm.setStatus(GameStatus.NEW);
        }
        this.update(id, mm);
        return true;
    }

    @Override
    public void dismiss(Long id, Long userId) {

    }

    @Override
    public void prepareGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#prepareGame] user [id: {}] want prepare game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseException("你不是房主, 无法准备");
        }
        GameStatus status = mm.getStatus();
        if (status.getCode() > 3) {
            throw new BaseException("重复操作, 请刷新再试");
        }
        if (mm.getStatus() != GameStatus.FULL) {
            throw new BaseException("人数还没满, 无法准备");
        }
        mm.setStatus(GameStatus.PREPARE);
        this.update(id, mm);
        // TODO 将多余的申请失效
    }

    @Override
    public void signGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#signGame] user [id: {}] want sign game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseException("游戏不存在");
        }
        GameStatus status = mm.getStatus();

        if (Objects.equals(mm.getUserId(), userId)) {
            if (status != GameStatus.PREPARE) {
                throw new BaseException("当前游戏状态, 无法开启签到");
            }
            // 如果是房主, 则开启签到
            mm.setStatus(GameStatus.SIGN_IN);
            mm.getSignInParticipant().add(userId);
        } else {
            if (status != GameStatus.SIGN_IN) {
                throw new BaseException("还未开始签到, 请联系房主开启签到");
            }
            // 如果不是房主, 则加入签到
            mm.getSignInParticipant().add(userId);
        }
        this.update(id, mm);
    }

    @Override
    public void startGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#startGame] user [id: {}] want start game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseException("游戏不存在");
        }
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseException("你不是房主, 无法开始游戏");
        }
        GameStatus status = mm.getStatus();
        if (status != GameStatus.SIGN_IN) {
            throw new RefreshException("请刷新后重试");
        }
        mm.setStatus(GameStatus.STARTING);
        this.update(id, mm);
    }

    @Override
    public void finishGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#finishGame] user [id: {}] want finish game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseException("游戏不存在");
        }
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseException("你不是房主, 无法结束游戏");
        }
        mm.setStatus(GameStatus.END);
        this.update(id, mm);
    }


    @Override
    public MurderMysteryDTO newDTO() {
        return new MurderMysteryDTO();
    }

    @Override
    public MurderMystery newEntity() {
        return new MurderMystery();
    }

    private boolean isFull(MurderMysteryDTO mm) {
        return Objects.equals(mm.getBoyParticipantNum(), mm.getBoyParticipantNum())
                && Objects.equals(mm.getGirlParticipantNum(), mm.getGirlParticipantNum());
    }

    private boolean tryRemovePerson(MurderMysteryDTO mm, Long userId, Gender gender) {
        return switch (gender) {
            case BOY -> {
                if (!mm.getBoyParticipant().contains(userId)) {
                    yield false;
                }
                mm.getBoyParticipant().remove(userId);
                yield true;
            }
            case GIRL -> {
                if (!mm.getGirlParticipant().contains(userId)) {
                    yield false;
                }
                mm.getGirlParticipant().remove(userId);
                yield true;
            }
        };
    }

    /**
     * 尝试添加人员, 如果添加成功, 返回true, 否则返回false
     *
     * @param mm     剧本杀游戏
     * @param userId 用户id
     * @param gender 用户性别
     * @return 是否添加成功
     */
    private boolean tryAddPerson(MurderMysteryDTO mm, Long userId, Gender gender) {
        return switch (gender) {
            case BOY -> {
                if (mm.getBoyParticipant().contains(userId)) {
                    yield false;
                }
                mm.getBoyParticipant().add(userId);
                yield true;
            }
            case GIRL -> {
                if (mm.getGirlParticipant().contains(userId)) {
                    yield false;
                }
                mm.getGirlParticipant().add(userId);
                yield true;
            }
        };
    }

    private void addBlemish(Long juInfoId, Long userId, String blemishReason) {
    }

    private void addApplyNotice(MurderMysteryDTO mm, Long userId, String applyReason) {
        MurderMysteryApplyDTO ja = new MurderMysteryApplyDTO();
        ja.setJuInfoId(mm.getId());
        ja.setUserId(userId);
        ja.setApplyReason(applyReason);
        ja.setApplyStatus(ApplyStatus.NEW);
        ja = juInfoApplyService.add(ja);
        mm.getApplyParticipant().add(ja.getJuInfoId());
    }


}
