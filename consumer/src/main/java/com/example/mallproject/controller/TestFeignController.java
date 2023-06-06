package com.example.mallproject.controller;

import api.Result;
import com.example.mallproject.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzzk1
 */
@RestController
public class TestFeignController {
    @Autowired
    private TestFeignService loginFeignService;

    @GetMapping("/consumer")
    public Result<String> login() {
        System.out.println("消费者:开始调用提供者");
        return loginFeignService.login();
    }
}
