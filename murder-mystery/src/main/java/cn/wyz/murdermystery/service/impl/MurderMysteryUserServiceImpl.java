package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.MurderMysteryUser;
import cn.wyz.murdermystery.bean.dto.MurderMysteryUserDTO;
import cn.wyz.murdermystery.mapper.MurderMysteryUserMapper;
import cn.wyz.murdermystery.service.MurderMysteryUserService;
import org.springframework.stereotype.Service;

@Service
public class MurderMysteryUserServiceImpl extends MapperServiceImpl<MurderMysteryUserMapper, MurderMysteryUser, MurderMysteryUserDTO> implements MurderMysteryUserService {
    @Override
    public MurderMysteryUserDTO newDTO() {
        return new MurderMysteryUserDTO();
    }

    @Override
    public MurderMysteryUser newEntity() {
        return new MurderMysteryUser();
    }
}
