package com.example.mallproject.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="provider",path = "/menu")
public interface MenuFeignService {

}
