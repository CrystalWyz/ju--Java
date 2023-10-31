package cn.wyz.user.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.user.bean.UserGame;
import cn.wyz.user.bean.dto.UserGameDTO;
import cn.wyz.user.mapper.UserGameMapper;
import cn.wyz.user.service.UserGameService;
import org.springframework.stereotype.Service;

/**
 * @author zhouzhitong
 * @since 2023-10-31
 **/
@Service
public class UserGameServiceImpl
        extends MapperServiceImpl<UserGameMapper, UserGame, UserGameDTO>
        implements UserGameService {


    @Override
    public UserGameDTO newDTO() {
        return new UserGameDTO();
    }

    @Override
    public UserGame newEntity() {
        return new UserGame();
    }
}
