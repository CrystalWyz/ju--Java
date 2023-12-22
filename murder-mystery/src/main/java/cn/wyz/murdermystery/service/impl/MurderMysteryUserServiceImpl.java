package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.MurderMysteryUser;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDTO;
import cn.wyz.murdermystery.mapper.MurderMysteryUserMapper;
import cn.wyz.murdermystery.service.MurderMysteryUserService;
import cn.wyz.murdermystery.type.BlemishDetailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MurderMysteryUserServiceImpl
        extends MapperServiceImpl<MurderMysteryUserMapper, MurderMysteryUser, MurderMysteryUserDTO>
        implements MurderMysteryUserService {

    @Override
    public boolean addCount(Long userId, Long gameId) {
        LOGGER.debug("add game Count userId:{}", userId);
        MurderMysteryUserDTO mmUser = getByUserId(userId);
        if (mmUser == null) {
            // TODO 是创建还是直接返回false？？？？
            return false;
        }
        mmUser.setCount(mmUser.getCount() + 1);
        update(mmUser);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCount(List<Long> userIds, Long gameId) {
        List<MurderMysteryUser> mmUsers = getByUserIds(userIds);
        mmUsers.forEach(mmUser -> mmUser.setCount(mmUser.getCount() + 1));
        boolean b = updateBatchById(mmUsers);
        return 1;
    }

    @Override
    public boolean addBlemishCount(Long userId, BlemishDetailType type) {
        LOGGER.debug("add blemish Count userId:{}, type:{}", userId, type);
        MurderMysteryUserDTO mmUser = getByUserId(userId);
        if (mmUser == null) {
            // TODO 是创建还是直接返回false？？？？
            return false;
        }
        mmUser.setBlemishCount(mmUser.getBlemishCount() + 1);
        update(mmUser);
        return true;
    }
}
