package com.example.mallproject.controller;

import api.Result;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.service.SpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuInfoService spuInfoService;

    @GetMapping("{id}")
    public Result<SpuInfo> getSpuById(@PathVariable int id) {
        return Result.Success(spuInfoService.getById(id));
    }

    @PostMapping
    public Result<Boolean> addSpu(@RequestBody SpuInfo spuInfo) {
        return Result.Success(spuInfoService.save(spuInfo));
    }

    @PutMapping("{id}")
    public Result<Boolean> updateSpu(@PathVariable Long id, @RequestBody SpuInfo spuInfo) {
        spuInfo.setId(id);
        return Result.Success(spuInfoService.updateById(spuInfo));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> deleteSpu(@PathVariable Long id) {
        return Result.Success(spuInfoService.removeById(id));
    }
}
