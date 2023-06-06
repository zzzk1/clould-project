package com.example.mallproject.controller;


import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Category;
import com.example.mallproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ValidatorUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page<Category>> getPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "") String name) {
        return Result.Success(categoryService.getPage(pageNum, pageSize, name), "查询成功");
    }

    @GetMapping
    public Result<List<Category>> getAll(@RequestParam(defaultValue = "") String name) {
        return Result.Success(categoryService.getAll(name));
    }

    @GetMapping("{id}")
    public Result<Category> getMenu(@PathVariable int id) {
        return Result.Success(categoryService.getById(id));
    }

    @GetMapping("ids")
    public Result<Stream<Long>> getIds() {
        return Result.Success(categoryService.list().stream().map(Category::getId));
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Category category) {
        ValidatorUtils.checkNull(category, "category");
        return Result.Success(categoryService.saveOrUpdate(category));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return Result.Success(categoryService.removeBatchByIds(ids));
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return Result.Success(categoryService.removeById(id));
    }


}
