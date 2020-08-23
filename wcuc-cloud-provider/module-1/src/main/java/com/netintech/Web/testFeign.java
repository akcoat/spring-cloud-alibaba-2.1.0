package com.netintech.Web;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netintech.FeignClients.LoginFeiginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testFeign {

    @Autowired
    private LoginFeiginClient schedualServiceHi;




//    @GetMapping("/testFeign")
//    public String testFeign() {
//        return schedualServiceHi.test();
//    }
//
//
//    @GetMapping("/testBreaker")
//    @HystrixCommand
//    public String testBreaker() {
//        return schedualServiceHi.test();
//    }


}
