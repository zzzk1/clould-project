package com.example.mallproject.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="provider",path = "/order")
public interface OrderFeignService {

}
