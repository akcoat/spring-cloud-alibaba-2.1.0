package com.netintech.transaction.controller;


import com.netintech.FeignClients.LoginFeiginClient;
import com.netintech.transaction.entity.Order;
import com.netintech.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
@RestController
@RequestMapping("/transaction/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private LoginFeiginClient loginFeiginClient;

    @PostMapping("updateOrder")
    public String updateOrder(@RequestParam("price")Integer price){
        Order order = new Order();
        order.setId(1).setPrice(1500);
        orderService.updatePrice(price);
        return "success";
    }

    @GetMapping("ids")
    public String test(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        String getids = loginFeiginClient.getids(integers);
        return  getids;
    }

    private static final Pattern VARIABLE_WITH_PROCESS_PATTERN = Pattern.compile("<[ ]*(([0-9A-Za-z]+)([ ]*,[ ]*([0-9A-Za-z]+))*)[ ]*,[ ]*process[ ]*>[ ]*(#[ ]*(([0-9a-zA-Z_:]+)|<([0-9a-zA-Z, ]+)>))?");
    private static final Pattern VARIABLES_PATTERN = Pattern.compile("(<[ ]*(([0-9A-Za-z]+)([ ]*,[ ]*([0-9A-Za-z]+))*)[ ]*>[ ]*(#[ ]*(([0-9a-zA-Z_:-]+)|<([0-9a-zA-Z, ]+)>))?)|('([^']+)')");

    public static void main(String[] args) {
//        String test  = "'test'" ;
//        Matcher matcher = VARIABLES_PATTERN.matcher(test);
//        System.out.println(matcher.groupCount());
//        if (matcher.find()){
//            for (int i = 1;i<=matcher.groupCount();i++){
//                System.out.println(matcher.group(i)+"------"+":"+i);
//            }
//        }
        String s = Arrays.asList("1").stream().reduce((fir, sec) -> sec).get();
        System.out.println(s);

    }


}

