package com.example.mallproject.feign;

import api.Result;
import com.example.mallproject.entity.SpuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/spu")
public interface SpuFeignService {

    @GetMapping("{id}")
    public Result<SpuInfo> getSpuById(@PathVariable("id") int id);

    @PostMapping
    public Result<Boolean> addSpu(@RequestBody SpuInfo spuInfo);

    @PutMapping("{id}")
    public Result<Boolean> updateSpu(@PathVariable("id") Long id, @RequestBody SpuInfo spuInfo);

    @DeleteMapping("{id}")
    public Result<Boolean> deleteSpu(@PathVariable("id") Long id);
}
