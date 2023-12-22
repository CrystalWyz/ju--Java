package cn.wyz.murdermystery.service;

import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.service.MapperService;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.murdermystery.bean.MurderMysteryUser;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDTO;
import cn.wyz.murdermystery.bean.request.MurderMysteryUserRequest;
import cn.wyz.murdermystery.type.BlemishDetailType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author wyz
 */
public interface MurderMysteryUserService extends MapperService<MurderMysteryUser, MurderMysteryUserDTO> {

    /**
     * 根据用户Id 获取游戏用户信息; 如果不存在就新增一个
     *
     * @param userId 用户Id
     * @return 游戏用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    default MurderMysteryUserDTO getByUserId(Long userId) {
        MurderMysteryUserRequest req = new MurderMysteryUserRequest();
        req.setUserId(userId);
        MurderMysteryUserDTO mmUser = getOne(req);
        if (mmUser == null) {
            mmUser = new MurderMysteryUserDTO();
            mmUser.setUserId(userId);
            mmUser.setGrade(1);
            mmUser.setCount(0);
            mmUser.setBlemishCount(0);
            mmUser = this.add(mmUser);
        }
        return mmUser;
    }

    default List<MurderMysteryUser> getByUserIds(List<Long> userIds) {
        MurderMysteryUserRequest req = new MurderMysteryUserRequest();
        FiledQuery fq = new FiledQuery();
        fq.setFiledName("userId");
        fq.setType(QueryType.IN);
        fq.setValue(userIds);
        req.getFiledQueries().add(fq);
        QueryWrapper<MurderMysteryUser> wrapper = buildQuery(req);
        return this.list(wrapper);
    }

    /**
     * 增加用户参加游戏场数
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean addCount(Long userId, Long gameId);

    /**
     * 增加用户参加游戏场数
     *
     * @param userIds 用户ID列表
     * @return 成功影响数
     */
    int addCount(List<Long> userIds, Long gameId);

    /**
     * 增加用户污点游戏场数
     *
     * @param userId 用户ID
     * @param type   污点类型
     * @return 是否成功
     */
    boolean addBlemishCount(Long userId, @Nullable BlemishDetailType type);

    @Override
    default MurderMysteryUserDTO newDTO() {
        return new MurderMysteryUserDTO();
    }

    @Override
    default MurderMysteryUser newEntity() {
        return new MurderMysteryUser();
    }

}
