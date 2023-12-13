package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.exception.BaseRefreshException;
import cn.wyz.common.exception.BaseRuntimeException;
import cn.wyz.common.exception.ResourceException;
import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryApplyDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.JoinGameReq;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.bo.MurderMysteryJoinBO;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.BlemishDetailService;
import cn.wyz.murdermystery.service.MurderMysteryApplyService;
import cn.wyz.murdermystery.service.MurderMysteryService;
import cn.wyz.murdermystery.type.ApplyStatus;
import cn.wyz.murdermystery.type.BlemishDetailType;
import cn.wyz.murdermystery.type.GameStatus;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.constant.Gender;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
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
            throw new BaseRuntimeException("游戏已经开始, 无法加入");
        }
        // 判断是否需要申请
        if (mm.needApply()) {
            addApplyNotice(mm, req.getReason());
        } else {
            // 判断已经加入的人数是否已经满了
            if (mm.isFull()) {
                throw new BaseRuntimeException("人数已满, 无法加入");
            }
            // 判断个人是否具备加入条件
            MurderMysteryJoinBO canJoin = canJoin(userId, gameId);
            if (!canJoin.isCanJoin()) {
                throw new BaseRuntimeException(canJoin.getReason());
            }
        }
        // 尝试新增
        if (tryAddPerson(mm, userId, user.getGender())) {
            throw new BaseRuntimeException("你已经加入, 请勿重复操作");
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
        if (mm.isFull()) {
            murderMysteryApplyService.invalidAll(mm.getId());
            throw new BaseRuntimeException("人数已满, 无法处理申请");
        }
        if (!mm.getApplyParticipant().contains(req.getApplyId())) {
            throw new BaseRefreshException("当前申请已经失效, 请刷新会重试");
        }
        UserDTO user = userService.get(req.getUserId());
        MurderMysteryApplyDTO mma = murderMysteryApplyService.get(req.getApplyId());
        if (mma.getApplyStatus() != ApplyStatus.NEW) {
            throw new BaseRefreshException("当前请求已经失效, 请刷新会重试");
        }
        if (req.getStatus() == ApplyStatus.NOT_PASS) {
            mma.setRejectReason(req.getReason());
        } else if (req.getStatus() == ApplyStatus.PASS) {
            Long userId = mma.getCreatedBy();
            MurderMysteryJoinBO canJoin = this.canJoin(userId, gameId);
            if (!canJoin.isCanJoin()) {
                LOGGER.error("[murderMystery#handleApply] user [id: {}] want join game [id: {}], but has conflict with game [{}]",
                        userId, mm.getId(), canJoin.getSource());
                murderMysteryApplyService.invalid(mma.getId(), canJoin.getReason());
                throw new BaseRefreshException(canJoin.getReason());
            }
            if (!tryAddPerson(mm, req.getUserId(), user.getGender())) {
                murderMysteryApplyService.invalid(mma.getId(), "该用户已经已经加入游戏了");
                throw new BaseRefreshException("该用户已经在游戏中了, 请勿重复操作");
            }
            // 更新申请信息
            mm.getApplyParticipant().remove(mma.getGameId());
            this.update(mm);
        } else if (req.getStatus() == ApplyStatus.CANCEL) {
            if (mma.getApplyStatus() != ApplyStatus.NEW) {
                throw new BaseRefreshException("当前状态无法撤销申请");
            }
        }
        mma.setApplyStatus(req.getStatus());
        murderMysteryApplyService.update(mma);
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
            addBlemish(id, userId, "在无法退出游戏状态时, 退出游戏", BlemishDetailType.LEAVE_EARLY);
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
        if (!Objects.equals(mm.getCreatedBy(), userId)) {
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
    public void startSignGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#signGame] user [id: {}] want sign game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseRuntimeException("游戏不存在");
        }
        GameStatus status = mm.getStatus();

        // 判断是否是房主
        if (Objects.equals(mm.getCreatedBy(), userId)) {
            if (status == GameStatus.SIGN_IN) {
                throw new BaseRefreshException("游戏已经开始签到, 请勿重复操作");
            }
            if (status != GameStatus.PREPARE) {
                throw new BaseRuntimeException("请先准备游戏, 再开启签到");
            }
            // 如果是房主, 则开启签到
            mm.setStatus(GameStatus.SIGN_IN);
            mm.getSignInParticipant().add(userId);
        } else {
            if (!(status == GameStatus.SIGN_IN || status == GameStatus.STARTING)) {
                throw new BaseRuntimeException("还未开始签到, 请联系房主开启签到");
            }
            // 如果不是房主, 则加入签到
            mm.getSignInParticipant().add(userId);

            // 判断是否是超时签到
            if (status == GameStatus.STARTING) {
                addBlemish(id, userId, "超时签到", BlemishDetailType.LATE);
            }
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
        if (!Objects.equals(mm.getCreatedBy(), userId)) {
            throw new BaseRuntimeException("你不是房主, 无法开始游戏");
        }
        GameStatus status = mm.getStatus();
        if (status == GameStatus.STARTING) {
            throw new BaseRefreshException("游戏已经开始, 请勿重复操作");
        }
        if (status != GameStatus.SIGN_IN) {
            throw new ResourceException("还未开始签到, 请联系房主开启签到");
        }
        mm.setStatus(GameStatus.STARTING);
        this.update(id, mm);

        // 处理未签到的人员
        List<Long> signInParticipant = mm.getSignInParticipant();
        List<Long> needParticipantList = mm.getAllParticipant();
        needParticipantList.removeAll(signInParticipant);
        for (Long l : needParticipantList) {
            addBlemish(id, l, "未签到", BlemishDetailType.ABSENT);
        }

    }

    @Override
    public void finishGame(Long id, Long userId) {
        LOGGER.info("[murderMystery#finishGame] user [id: {}] want finish game [id: {}] ", userId, id);
        MurderMysteryDTO mm = this.get(id);
        if (mm == null) {
            throw new BaseRuntimeException("游戏不存在");
        }
        if (!Objects.equals(mm.getCreatedBy(), userId)) {
            throw new BaseRuntimeException("你不是房主, 无法结束游戏");
        }
        mm.setStatus(GameStatus.END);
        this.update(id, mm);
    }

    @Override
    public MurderMysteryJoinBO canJoin(Long userId, Long gameId) {
        LOGGER.trace("[murderMystery#checkUserCanJoinNew] user [id: {}] want check can join new game", userId);
        MurderMysteryDTO mm = tryGetConflictGame(userId, gameId);
        if (mm != null) {
            return MurderMysteryJoinBO.of(mm, "你已经与已经加入的游戏" + mm.getTitle() + " 时间有冲突, 无法加入");
        }
        // TODO 判断是否有污点？？？

        return new MurderMysteryJoinBO();
    }

    @Override
    public MurderMysteryDTO tryGetConflictGame(Long userId, Long gameId) {
        LOGGER.trace("[murderMystery#checkUserCanJoinNew] user [id: {}] want check can join new game", userId);
        MurderMystery mm = this.getById(gameId);
        if (mm == null) {
            throw new BaseRuntimeException("游戏不存在");
        }

        MurderMysteryRequest req = new MurderMysteryRequest();
        req.setCreateBy(userId);
        req.getFiledQueries().add(FiledQuery.of("status", GameStatus.NEW, QueryType.GE));
        List<MurderMysteryDTO> startingMMList = this.queryAll(req);
        if (CollectionUtils.isEmpty(startingMMList)) {
            return null;
        }
        // 获取 startingMMList, 最小的开始时间, 和最大的结束时间(都要大于当前时间的)
        LocalDateTime minStartTime = LocalDateTime.now();
        LocalDateTime maxFinishTime = minStartTime;
        MurderMysteryDTO minStartTimeMM = null;
        MurderMysteryDTO maxStartTimeMM = null;


        for (MurderMysteryDTO startingMM : startingMMList) {
            // 判断预计开始时间相比现在过了一天, 则过滤掉
            if (startingMM.getBeginExpected().isAfter(LocalDateTime.now().plusDays(1))) {
                continue;
            }
            LocalDateTime gameStartTime = startingMM.getBeginActual() != null ?
                    startingMM.getBeginActual() : startingMM.getBeginExpected();
            LocalDateTime gameFinishTime = startingMM.getFinishExpected();
            if (gameStartTime.isBefore(minStartTime)) {
                minStartTime = gameStartTime;
                minStartTimeMM = startingMM;
            }
            if (gameFinishTime.isAfter(maxFinishTime)) {
                maxFinishTime = gameFinishTime;
                maxStartTimeMM = startingMM;
            }
        }
        if (minStartTimeMM == null || maxStartTimeMM == null) {
            return null;
        }
        // 判断当前游戏的预计开始时间和预计结束时间, 不在最小开始时间和最大结束时间之间, 则可以加入
        LocalDateTime gameStartTime = mm.getBeginExpected();
        LocalDateTime gameFinishTime = mm.getFinishExpected();
        if (gameStartTime.isAfter(minStartTime)) {
            return minStartTimeMM;
        }
        if (gameFinishTime.isBefore(maxFinishTime)) {
            return maxStartTimeMM;
        }
        return null;
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
            case UNKNOWN -> true;
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
            case UNKNOWN -> false;
        };

    }

    private void addBlemish(Long juInfoId, Long userId, String blemishReason, BlemishDetailType type) {
        BlemishDetailDTO bd = new BlemishDetailDTO();
        bd.setJuInfoId(juInfoId);
        bd.setUserId(userId);
        bd.setDescription(blemishReason);
        bd.setType(type);
        blemishDetailService.add(bd);
    }

    private void addApplyNotice(MurderMysteryDTO mm, String applyReason) {
        MurderMysteryApplyDTO ja = new MurderMysteryApplyDTO();
        ja.setGameId(mm.getId());
        ja.setApplyReason(applyReason);
        ja.setApplyStatus(ApplyStatus.NEW);
        ja = murderMysteryApplyService.add(ja);
        mm.getApplyParticipant().add(ja.getGameId());
    }


}
