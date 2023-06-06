package com.example.mallproject.feign;

import api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/test")
public interface TestFeignService {

    @RequestMapping("/login")
    public Result<String> login();
}
