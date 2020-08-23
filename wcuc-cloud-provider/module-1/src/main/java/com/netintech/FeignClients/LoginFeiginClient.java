package com.netintech.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "wcuc-cloud-login")
public interface LoginFeiginClient {


    @PostMapping("order/updateOrder")
     String updateOrder(@RequestParam("price")Integer price);

    @GetMapping("order/ids")
    String getids(@RequestParam("ids") List<Integer> ids);
}