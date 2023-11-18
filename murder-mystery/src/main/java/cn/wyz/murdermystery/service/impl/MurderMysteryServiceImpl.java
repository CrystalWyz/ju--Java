package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseRuntimeException;
import cn.wyz.common.exception.ResourceException;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
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
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * @author wyz
 */
@Service
@Slf4j
@AllArgsConstructor
public class MurderMysteryServiceImpl extends MapperServiceImpl<MurderMysteryMapper, MurderMystery, MurderMysteryDTO> implements MurderMysteryService {

    private final MurderMysteryApplyService murderMysteryApplyService;

    private final UserService userService;

    private final BlemishDetailService blemishDetailService;

//    @Override
//    public <Query extends BaseRequest> PageResultVO<MurderMysteryDTO> page(Query query) {
//        MurderMysteryRequest req = (MurderMysteryRequest) query;
//        PageResultVO<MurderMysteryDTO> page = super.page(query);
//
//
//        return page;
//    }

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
            throw new BaseRuntimeException("当前游戏状态, 无法加入");
        }
        if (mm.needApply()) {
            addApplyNotice(mm, userId, req.getReason());
        } else {
            if (tryAddPerson(mm, userId, user.getGender())) {
                throw new BaseRuntimeException("你已经加入, 请勿重复操作");
            }
        }
        this.update(gameId, mm);
    }

    @Override
    public void handleApply(HandleApplyGameReq req) {
        LOGGER.info("[murderMystery#handleApply] req {}", req);
        Long gameId = req.getGameId();
        MurderMysteryDTO mm = this.get(gameId);
        if (mm.getStatus() != GameStatus.NEW) {
            throw new BaseRuntimeException("当前游戏状态, 无法处理申请");
        }
        UserDTO user = userService.get(req.getUserId());
        MurderMysteryApplyDTO mma = murderMysteryApplyService.get(req.getApplyId());
        if (req.getStatus() == ApplyStatus.NOT_PASS) {
            mma.setRejectReason(req.getReason());
        } else if (req.getStatus() == ApplyStatus.PASS) {
            if (!tryAddPerson(mm, req.getUserId(), user.getGender())) {
                // 当前性别的人数满了
                throw new BaseRuntimeException("当前性别的人数已满, 无法加入");
            }
            this.update(mm);
        } else if (req.getStatus() == ApplyStatus.CANCEL) {
            if (mma.getApplyStatus() != ApplyStatus.NEW) {
                throw new BaseRuntimeException("当前状态无法撤销申请");
            }
        }
        mma.setApplyStatus(req.getStatus());
        murderMysteryApplyService.update(mma);
    }

    @Override
    public void cancelApply(JoinGameReq req) {
        LOGGER.info("[murderMystery#cancelApply] req {}", req);

    }

    @Override
    public boolean outGame(Long id, Long userId, Boolean isForce) {
        LOGGER.info("[murderMystery#outGame] user [id: {}] want out game [id: {}, isForce: {}] ",
                userId, id, isForce);
        MurderMysteryDTO mm = this.get(id);
        GameStatus status = mm.getStatus();

        if (!status.canOut() && !isForce) {
            throw new BaseRuntimeException("当前游戏状态不支持退出");
        }
        // 如果是在无法退出游戏状态时, 则记录有瑕疵
        boolean hasBlemish = !status.canOut();
        UserDTO user = userService.get(userId);
        if (tryRemovePerson(mm, userId, user.getGender())) {
            throw new BaseRuntimeException("你已不在游戏中, 无法退出");
        }
        if (hasBlemish) {
            addBlemish(id, userId, "在无法退出游戏状态时, 退出游戏");
        }
        this.update(id, mm);
        return true;
    }

    @Override
    public void dismiss(Long id, Long userId) {
        LOGGER.info("[murderMystery#dismiss] user [id: {}] want dismiss game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        // FIXME 判断什么情况下可以解散游戏
        mm.setStatus(GameStatus.DISMISS);
        // 失效所有申请
        if (!CollectionUtils.isEmpty(mm.getApplyParticipant())) {
            murderMysteryApplyService.invalidAll(id);
        }
        this.update(mm);
    }

    @Override
    public void prepareGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#prepareGame] user [id: {}] want prepare game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseRuntimeException("你不是房主, 无法准备");
        }
        GameStatus status = mm.getStatus();
        if (status.getCode() > 3) {
            throw new BaseRuntimeException("重复操作, 请刷新再试");
        }
        if (mm.isFull()) {
            throw new BaseRuntimeException("人数还没满, 无法准备");
        }
        mm.setStatus(GameStatus.PREPARE);
        this.update(id, mm);
        // 将多余的申请失效
        murderMysteryApplyService.invalidAll(id);
    }

    @Override
    public void signGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#signGame] user [id: {}] want sign game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseRuntimeException("游戏不存在");
        }
        GameStatus status = mm.getStatus();

        if (Objects.equals(mm.getUserId(), userId)) {
            if (status != GameStatus.PREPARE) {
                throw new BaseRuntimeException("当前游戏状态, 无法开启签到");
            }
            // 如果是房主, 则开启签到
            mm.setStatus(GameStatus.SIGN_IN);
            mm.getSignInParticipant().add(userId);
        } else {
            if (status != GameStatus.SIGN_IN) {
                throw new BaseRuntimeException("还未开始签到, 请联系房主开启签到");
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
            throw new BaseRuntimeException("游戏不存在");
        }
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseRuntimeException("你不是房主, 无法开始游戏");
        }
        GameStatus status = mm.getStatus();
        if (status != GameStatus.SIGN_IN) {
            throw new ResourceException("请刷新后重试");
        }
        mm.setStatus(GameStatus.STARTING);
        this.update(id, mm);
    }

    @Override
    public void finishGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#finishGame] user [id: {}] want finish game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseRuntimeException("游戏不存在");
        }
        if (!Objects.equals(mm.getUserId(), userId)) {
            throw new BaseRuntimeException("你不是房主, 无法结束游戏");
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
        ja.setGameId(mm.getId());
        ja.setUserId(userId);
        ja.setApplyReason(applyReason);
        ja.setApplyStatus(ApplyStatus.NEW);
        ja = murderMysteryApplyService.add(ja);
        mm.getApplyParticipant().add(ja.getGameId());
    }


}
