package cn.wyz.murdermystery.service.handler;

import cn.hutool.core.lang.Pair;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDetailDTO;
import cn.wyz.murdermystery.service.BlemishDetailService;
import cn.wyz.murdermystery.service.MurderMysteryUserService;
import cn.wyz.murdermystery.type.BlemishDetailType;
import cn.wyz.user.handler.UserDetailHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhouzhitong
 * @since 2024-03-16
 **/
@Service
@Slf4j
@AllArgsConstructor
public class MurderMysteryUserDetailHandler implements UserDetailHandler {

    private final MurderMysteryUserService mmUserService;

    private final BlemishDetailService blemishDetailService;

    @Override
    public Pair<String, Object> getUserDetail(Long userId) {
        LOGGER.debug("query MurderMystery user {} detail.", userId);
        MurderMysteryUserDetailDTO userDetail = new MurderMysteryUserDetailDTO();

        MurderMysteryUserDTO mmUser = mmUserService.getByUserId(userId);
        userDetail.setMMUser(mmUser);
        List<BlemishDetailDTO> blemishDetails = blemishDetailService.getByUserId(userId);
        Map<BlemishDetailType, Long> blemishCountMap = blemishDetails.stream()
                .collect(Collectors.groupingBy(BlemishDetailDTO::getType, Collectors.counting()));
        userDetail.setBlemishCountMap(blemishCountMap);
        return Pair.of("murderMystery", userDetail);
    }

}
