package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.MurderMysteryUser;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDTO;
import cn.wyz.murdermystery.bean.request.HandleApplyGameReq;
import cn.wyz.murdermystery.bo.MurderMysteryJoinBO;
import cn.wyz.user.context.LoginContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyz
 */
public interface MurderMysteryUserService extends MapperService<MurderMysteryUser, MurderMysteryUserDTO> {
}
