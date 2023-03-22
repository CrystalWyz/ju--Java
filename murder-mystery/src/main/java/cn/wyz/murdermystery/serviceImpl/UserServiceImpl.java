package cn.wyz.murdermystery.serviceImpl;

import cn.wyz.murdermystery.bean.User;
import cn.wyz.murdermystery.mapper.UserMapper;
import cn.wyz.murdermystery.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
