package com.example.mallproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import api.Result;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "getById")
    public Result<Page<SpuInfo>> getSpuByBrandId(@RequestParam(value = "id", defaultValue = "2") int id,
                                                 @RequestParam(value = "curr", defaultValue = "1") int curr,
                                                 @RequestParam(value = "size", defaultValue = "5") int size) {
        return Result.Success(brandService.getSpuInfo(id, curr, size));
    }


}
