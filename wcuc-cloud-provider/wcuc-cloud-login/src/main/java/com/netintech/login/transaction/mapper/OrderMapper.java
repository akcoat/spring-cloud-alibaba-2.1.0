package com.netintech.login.transaction.mapper;

import com.netintech.login.transaction.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
public interface OrderMapper extends BaseMapper<Order> {


    void updatePirce(int price);
}
