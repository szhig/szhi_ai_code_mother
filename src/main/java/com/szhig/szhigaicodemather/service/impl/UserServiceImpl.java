package com.szhig.szhigaicodemather.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.szhig.szhigaicodemather.model.entity.User;
import com.szhig.szhigaicodemather.mapper.UserMapper;
import com.szhig.szhigaicodemather.service.UserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author <a href="https://github.com/szhig"></a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

}
