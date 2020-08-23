package com.netintech.login.transaction.service.impl;

import com.netintech.login.transaction.entity.Order;
import com.netintech.login.transaction.mapper.OrderMapper;
import com.netintech.login.transaction.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Override
    public void updatePrice(int price) {
        orderMapper.updatePirce(price);
    }
}
