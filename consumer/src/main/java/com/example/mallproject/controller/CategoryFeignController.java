package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Category;
import com.example.mallproject.feign.CategoryFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ValidatorUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("category")
public class CategoryFeignController {

    @Autowired
    private CategoryFeignService categoryService;

    @GetMapping("/page")
    public Result<Page<Category>> getPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "") String name) {
        return categoryService.getPage(pageNum, pageSize, name);
    }

    @GetMapping
    public Result<List<Category>> getAll(@RequestParam(defaultValue = "") String name) {
        return categoryService.getAll(name);
    }

    @GetMapping("{id}")
    public Result<Category> getMenu(@PathVariable("id") int id) {
        return categoryService.getMenu(id);
    }

    @GetMapping("ids")
    public Result<Stream<Long>> getIds() {
        return categoryService.getIds();
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Category category) {
        ValidatorUtils.checkNull(category, "category");
        return categoryService.edit(category);
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return categoryService.deleteBatchId(ids);
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable("id") long id) {
        return categoryService.delete(id);
    }
}
