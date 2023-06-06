package com.example.mallproject.feign;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.SpuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/brand")
public interface BrandFeignService {

    @GetMapping(value = "getById")
    Result<Page<SpuInfo>> getSpuByBrandId(@RequestParam(value = "id", defaultValue = "2") int id,
                                                 @RequestParam(value = "curr", defaultValue = "1") int curr,
                                                 @RequestParam(value = "size", defaultValue = "5") int size);
}
