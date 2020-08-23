package com.netintech.transaction.service.impl;

import com.netintech.FeignClients.LoginFeiginClient;
import com.netintech.transaction.entity.Order;
import com.netintech.transaction.mapper.OrderMapper;
import com.netintech.transaction.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private LoginFeiginClient loginFeiginClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void updatePrice(int price) {
        loginFeiginClient.updateOrder(1900);
        orderMapper.updatePirce(price);
        int i = 1/0;
    }
}
