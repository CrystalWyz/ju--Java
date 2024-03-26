package cn.wyz.murdermystery.service.impl;

import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.event.EventPublisher;
import cn.wyz.common.exception.AppException;
import cn.wyz.common.exception.BaseRefreshException;
import cn.wyz.common.exception.BaseRuntimeException;
import cn.wyz.common.exception.ResourceException;
import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.MurderMysteryUser;
import cn.wyz.murdermystery.bean.dto.*;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bean.request.MurderMysteryRequest;
import cn.wyz.murdermystery.bean.bo.MurderMysteryJoinBO;
import cn.wyz.murdermystery.constant.MurderMysteryConstant;
import cn.wyz.murdermystery.event.MurderMysteryPrepareEvent;
import cn.wyz.murdermystery.mapper.MurderMysteryMapper;
import cn.wyz.murdermystery.service.*;
import cn.wyz.murdermystery.type.ApplyStatus;
import cn.wyz.murdermystery.type.BlemishDetailType;
import cn.wyz.murdermystery.type.GameStatus;
import cn.wyz.murdermystery.type.ServiceType;
import cn.wyz.user.bean.User;
import cn.wyz.user.bean.dto.UserDTO;
import cn.wyz.user.constant.Gender;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.holder.SecurityContextHolder;
import cn.wyz.user.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private final MurderMysteryUserService murderMysteryUserService;

    private final MurderMysteryCacheService murderMysteryCacheService;

    private final EventPublisher eventPublisher;

    private final TagService tagService;

    @Override
    public MurderMysteryDTO add(MurderMysteryDTO dto) {
        LoginContext context = SecurityContextHolder.getContext();

        // 时间冲突检查
        timeConflictCheck(context, dto.getBeginExpected());
        // 把自己添加进去
        tryAddPerson(dto, context.getUserId(), context.getGender());
        dto.setStatus(GameStatus.NEW);
        MurderMysteryDTO res = super.add(dto);
        tryAddTag(dto);
        return res;
    }

    @Override
    public MurderMysteryDTO update(Long id, MurderMysteryDTO dto) {
        LOGGER.info("update request: {}", dto);
        MurderMystery entity = getEntity(id);

        // TODO 判断修改的属性, 在当前的游戏状态下能否修改
        copyProperties(dto, entity);

        entity.setUpdateTime(LocalDateTime.now());
        entity.setLastModifiedBy(systemProvider.getCurrentUserId());
        // 修改标签
        tryAddTag(dto);

        boolean update = this.updateById(entity);
        return update
                ? toDTO(entity)
                : null;
    }

    @Override
    public void join(Long gameId) {
        LoginContext context = SecurityContextHolder.getContext();
        LOGGER.info("[murderMystery#join] user [id: {}] want joint game [id: {}]", context.getUserId(), gameId);
        MurderMysteryDTO mm = this.get(gameId);
        if (mm.getStatus() != GameStatus.NEW) {
            throw new BaseRuntimeException("游戏已经开始, 无法加入");
        }

        // 判断已经加入的人数是否已经满了
        boolean isFull = switch (context.getGender()) {
            case BOY -> mm.isBoyFull();
            case GIRL -> mm.isGirlFull();
            case UNKNOWN -> throw new AppException(CommonStatusEnum.FAIL, "请先完善个人信息");
        };
        if (isFull) {
            throw new BaseRuntimeException("人数已满, 无法加入");
        }

        // 判断个人是否具备加入条件
        MurderMysteryJoinBO canJoin = canJoin(context, gameId);
        if (canJoin.isCanJoin()) {
            if (mm.needApply()) {
                // 需要审核
                addApplyNotice(mm);
            } else {
                // 尝试新增
                if (!tryAddPerson(mm, context.getUserId(), context.getGender())) {
                    throw new BaseRuntimeException("你已经加入, 请勿重复操作");
                }
            }
            this.update(gameId, mm);
        } else {
//            throw new BaseRuntimeException(canJoin.getReason());
        }
    }

    @Override
    public void handleApply(HandleApplyGameReq req) {
        LOGGER.info("[murderMystery#handleApply] req {}", req);
        Long gameId = req.getGameId();
        LoginContext context = SecurityContextHolder.getContext();
        MurderMysteryDTO mm = this.get(gameId);
        if (mm.getStatus() != GameStatus.NEW) {
            throw new BaseRuntimeException("当前游戏状态, 无法处理申请");
        }
        if (mm.isFull()) {
            // 应该在游戏开始的时候, 就把所有的申请失效
//            murderMysteryApplyService.invalidAll(mm.getId(), "人数已满, 无法处理申请");
            throw new BaseRuntimeException("人数已满, 无法处理申请");
        }
        if (mm.getApplyParticipant().remove(req.getApplyId())) {
            throw new BaseRefreshException("请求已经处理, 请刷新后再试");
        }
        UserDTO user = userService.get(context.getUserId());
        MurderMysteryApplyDTO mma = murderMysteryApplyService.get(req.getApplyId());
        if (mma.getApplyStatus() != ApplyStatus.NEW) {
            throw new BaseRefreshException("当前请求已经失效, 请勿重复操作");
        }
        if (req.getStatus() == ApplyStatus.NOT_PASS) {
            mma.setRejectReason(req.getReason());
        } else if (req.getStatus() == ApplyStatus.PASS) {
            Long userId = mma.getCreatedBy();
            MurderMysteryJoinBO canJoin = this.canJoin(context, gameId);
            if (!canJoin.isCanJoin()) {
                LOGGER.error("[murderMystery#handleApply] user [id: {}] want join game [id: {}], but has conflict with game [{}]", userId, mm.getId(), canJoin.getSource());
                murderMysteryApplyService.invalid(mma.getId(), canJoin.getReason());
                throw new BaseRefreshException(canJoin.getReason());
            }
            if (!tryAddPerson(mm, context.getUserId(), user.getGender())) {
                murderMysteryApplyService.invalid(mma.getId(), "该用户已经已经加入游戏了");
                throw new BaseRefreshException("该用户已经在游戏中, 请勿重复操作");
            }
            // 更新申请信息
            mm.getApplyParticipant().remove(mma.getGameId());
        }
        this.update(mm);
        mma.setApplyStatus(req.getStatus());
        murderMysteryApplyService.update(mma);
    }

    @Override
    public boolean outGame(Long id, Long userId, Boolean isForce) {
        LOGGER.info("[murderMystery#outGame] user [id: {}] want out game [id: {}, isForce: {}] ", userId, id, isForce);
        MurderMysteryDTO mm = this.get(id);
        GameStatus status = mm.getStatus();

        if (!status.canOut() && !isForce) {
            throw new BaseRuntimeException("当前游戏状态不支持退出");
        }
        // 如果是在无法退出游戏状态时, 则记录有瑕疵
        boolean hasBlemish = !status.canOut();
        UserDTO user = userService.get(userId);
        if (!tryRemovePerson(mm, userId, user.getGender())) {
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
            murderMysteryApplyService.invalidAll(id, "游戏已经解散");
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

        mm.getApplyParticipant().clear();

        eventPublisher.publish(new MurderMysteryPrepareEvent(mm));

        // FIXME 将下面的逻辑应该通过事件实现解偶
        // 将多余的申请失效
        murderMysteryApplyService.invalidAll(id, "人数已满, 无法处理申请");

        // 给所有的用户增加游戏常数
        List<Long> allParticipant = mm.getAllParticipant();
        murderMysteryUserService.addCount(allParticipant, id);
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
            murderMysteryUserService.addBlemishCount(userId, BlemishDetailType.ABSENT);
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
    public MurderMysteryJoinBO canJoin(LoginContext context, Long gameId) {
        LOGGER.trace("[murderMystery#checkUserCanJoinNew] user [id: {}] want check can join new game", context.getUserId());
        MurderMystery murderMystery = this.getById(gameId);
        JSONObject config = murderMystery.getConfig();

        // 时间冲突判断
        MurderMystery conflict = baseMapper.getUserConflictJoined(context, murderMystery.getBeginExpected());
        if (conflict != null) {
            return MurderMysteryJoinBO.of(conflict, "与游戏" + conflict.getTitle() + " 时间冲突, 无法加入");
        }

        // 用户信息判断
        Integer level = config.getInteger(MurderMysteryConstant.LEVEL.getValue());
        if (ObjectUtils.isNotEmpty(level)) {

        }

        return new MurderMysteryJoinBO();
    }

    @Override
    public MurderMysteryDTO tryGetConflictGame(Long userId, Long gameId) {
        LOGGER.trace("[murderMystery#checkUserCanJoinNew] user [id: {}] want check can join new game", userId);
        MurderMystery targetMM = this.getById(gameId);
        if (targetMM == null) {
            throw new BaseRuntimeException("游戏不存在");
        }

        MurderMysteryRequest req = new MurderMysteryRequest();
        req.setCreatedBy(userId);
        req.getFiledQueries().add(FiledQuery.of("status", GameStatus.NEW, QueryType.GE));
        // 已经加入的游戏
        List<MurderMysteryDTO> joinedMMList = this.queryAll(req);
        if (CollectionUtils.isEmpty(joinedMMList)) {
            return null;
        }
        // 获取 startingMMList, 最小的开始时间, 和最大的结束时间(都要大于当前时间的)
        LocalDateTime startTime = targetMM.getBeginActual() != null ? targetMM.getBeginActual() : targetMM.getBeginExpected();

        LocalDateTime finishTime = targetMM.getFinishExpected();

        // 间隔时间: 单位小时
        int intervalHour = 1;
        for (MurderMysteryDTO joinedMM : joinedMMList) {
            LocalDateTime gameStartTime = joinedMM.getBeginActual() != null ? joinedMM.getBeginActual() : joinedMM.getBeginExpected();
            LocalDateTime gameFinishTime = joinedMM.getFinishExpected();

            // 过滤掉过期的游戏: 游戏的结束时间 < 当前时间 - intervalHour
            if (gameFinishTime.isBefore(LocalDateTime.now().minusHours(intervalHour))) {
                continue;
            }
            // 判断 targetMM 是否与 startingMM 时间冲突, 并且这个时间冲突大于 intervalHour 值
            // [startTime, finishTime] 和 [gameStartTime, gameFinishTime] 是否有交集
            if (gameStartTime.minusHours(intervalHour).isBefore(startTime) && gameFinishTime.minusHours(-intervalHour).isAfter(startTime)) {
                return joinedMM;
            }
            if (gameStartTime.minusHours(intervalHour).isBefore(finishTime) && gameFinishTime.minusHours(-intervalHour).isAfter(finishTime)) {
                return joinedMM;
            }
        }
        return null;
    }

    private void timeConflictCheck(LoginContext userInfo, LocalDateTime startTime) {
        MurderMystery murderMystery = baseMapper.getUserConflictJoined(userInfo, startTime);
        if (ObjectUtils.isNotEmpty(murderMystery)) {
            throw new AppException(CommonStatusEnum.FAIL, "时间冲突:" + murderMystery.getTitle());
        }
    }

    private int tryAddTag(MurderMysteryDTO dto) {
        List<String> tagNames = dto.getTags();
        List<TagDTO> tags = tagNames.stream()
                .map(tagName -> new TagDTO(tagName, 1, ServiceType.MurderMystery))
                .toList();

        return tagService.addTags(tags);
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

    private void addApplyNotice(MurderMysteryDTO mm) {
        MurderMysteryApplyDTO ja = new MurderMysteryApplyDTO();
        ja.setGameId(mm.getId());
        ja.setApplyStatus(ApplyStatus.NEW);
        ja = murderMysteryApplyService.add(ja);
        mm.getApplyParticipant().add(ja.getGameId());
    }

    @Override
    public MurderMysteryDTO get(Long id) {
        Long reviewUserId = SecurityContextHolder.getContext().getUserId();
        // 获取剧本杀详情
        MurderMystery murderMystery = getById(id);
        MurderMysteryDTO murderMysteryDTO = toDTO(murderMystery);

        // 增加浏览次数
        murderMysteryCacheService.addReview(id, reviewUserId);

        // 获取用户信息
        Collection<Long> participants = CollectionUtils.union(murderMystery.getBoyParticipant(), murderMystery.getGirlParticipant());
        // 用户主表查询
        Map<Long, User> userMap = userService.getAllByIds(participants);

        // 剧本杀用户表查询
        Map<Long, MurderMysteryUser> murderMysteryUserMap = murderMysteryUserService.listByIds(participants)
                .stream()
                .collect(Collectors.toMap(MurderMysteryUser::getId, Function.identity()));

        // 信息组装
        murderMysteryDTO.setCreateUsername(userMap.get(murderMysteryDTO.getCreatedBy()).getUsername());
        List<MurderMysteryUserDTO> boyParticipants = murderMystery.getBoyParticipant().stream()
                .map(userId -> {
                    User user = userMap.get(userId);
                    MurderMysteryUser murderMysteryUser = murderMysteryUserMap.get(userId);

                    MurderMysteryUserDTO murderMysteryUserDTO = new MurderMysteryUserDTO();
                    murderMysteryUserDTO.setUserId(userId);
                    murderMysteryUserDTO.setUsername(user.getUsername());
                    murderMysteryUserDTO.setGender(user.getGender());
                    if (ObjectUtils.isNotEmpty(murderMysteryUser)) {
                        murderMysteryUserDTO.setGrade(murderMysteryUser.getGrade());
                        murderMysteryUserDTO.setCount(murderMysteryUser.getCount());
                        murderMysteryUserDTO.setBlemishCount(murderMysteryUser.getBlemishCount());
                    }


                    return murderMysteryUserDTO;
                }).toList();
        List<MurderMysteryUserDTO> girlParticipants = murderMystery.getGirlParticipant().stream()
                .map(userId -> {
                    User user = userMap.get(userId);
                    MurderMysteryUser murderMysteryUser = murderMysteryUserMap.get(userId);

                    MurderMysteryUserDTO murderMysteryUserDTO = new MurderMysteryUserDTO();
                    murderMysteryUserDTO.setUserId(userId);
                    murderMysteryUserDTO.setUsername(user.getUsername());
                    murderMysteryUserDTO.setGender(user.getGender());
                    if (ObjectUtils.isNotEmpty(murderMysteryUser)) {
                        murderMysteryUserDTO.setGrade(murderMysteryUser.getGrade());
                        murderMysteryUserDTO.setCount(murderMysteryUser.getCount());
                        murderMysteryUserDTO.setBlemishCount(murderMysteryUser.getBlemishCount());
                    }

                    return murderMysteryUserDTO;
                }).toList();
        murderMysteryDTO.setBoyParticipants(boyParticipants);
        murderMysteryDTO.setGirlParticipants(girlParticipants);

        return murderMysteryDTO;
    }

    @Override
    public MurderMystery getById(Serializable id) {
        MurderMystery mm = super.getById(id);
        mm.setReviews(murderMysteryCacheService.getReviewCount(id));
        return mm;
    }

}
