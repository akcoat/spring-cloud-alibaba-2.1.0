package com.netintech.login.test.service.impl;

import com.netintech.login.test.entity.Users;
import com.netintech.login.test.mapper.UsersMapper;
import com.netintech.login.test.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjw
 * @since 2020-04-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
