package com.netintech.login.transaction.service;

import com.netintech.login.transaction.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
public interface OrderService extends IService<Order> {

    void updatePrice(int price);

}
