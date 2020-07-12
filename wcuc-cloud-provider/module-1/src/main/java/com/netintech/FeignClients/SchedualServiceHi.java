package com.netintech.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "wcuc-cloud-login")
public interface SchedualServiceHi {


    @GetMapping("/test")
    String test();
}