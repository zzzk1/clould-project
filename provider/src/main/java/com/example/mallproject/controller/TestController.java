package com.example.mallproject.controller;

import api.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/login")
    public Result<String> login() {
        System.out.println("提供者");
        return Result.Success("提供者");
    }
}
