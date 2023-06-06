package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.feign.BrandFeignService;
import com.example.mallproject.feign.SpuFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("/brand")
public class SpuFeignController {
    @Autowired
    private SpuFeignService spuService;

    @GetMapping("{id}")
    public Result<SpuInfo> getSpuById(@PathVariable int id) {
        return spuService.getSpuById(id);
    }

    @PostMapping
    public Result<Boolean> addSpu(@RequestBody SpuInfo spuInfo) {
        return spuService.addSpu(spuInfo);
    }

    @PutMapping("{id}")
    public Result<Boolean> updateSpu(@PathVariable Long id, @RequestBody SpuInfo spuInfo) {
        return spuService.updateSpu(id, spuInfo);
    }

    @DeleteMapping("{id}")
    public Result<Boolean> deleteSpu(@PathVariable Long id) {
        return spuService.deleteSpu(id);
    }
}
