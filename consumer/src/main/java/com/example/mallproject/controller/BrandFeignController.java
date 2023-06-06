package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.feign.BrandFeignService;
import com.example.mallproject.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("/brand")
public class BrandFeignController {
    @Autowired
    private BrandFeignService brandFeignService;

    @GetMapping(value = "getById")
    public Result<Page<SpuInfo>> getSpuByBrandId(@RequestParam(value = "id", defaultValue = "2") int id,
                                                 @RequestParam(value = "curr", defaultValue = "1") int curr,
                                                 @RequestParam(value = "size", defaultValue = "5") int size) {
        return brandFeignService.getSpuByBrandId(id, curr, size);
    }
}
