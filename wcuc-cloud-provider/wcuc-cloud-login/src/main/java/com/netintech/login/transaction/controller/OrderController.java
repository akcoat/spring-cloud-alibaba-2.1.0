package com.netintech.login.transaction.controller;


import com.netintech.login.transaction.entity.Order;
import com.netintech.login.transaction.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("updateOrder")
    public String updateOrder(@RequestParam("price")Integer price){
        Order order = new Order();
        order.setId(2).setPrice(price);
        orderService.updatePrice(price);
        return "success";
    }

    @GetMapping("ids")
    public String getIds(@RequestParam("ids") List<Integer> ids){
        return ids.toString();
    }

}

