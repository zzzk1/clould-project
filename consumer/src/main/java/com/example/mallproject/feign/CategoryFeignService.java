package com.example.mallproject.feign;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/category")
public interface CategoryFeignService {

    @GetMapping("/page")
    public Result<Page<Category>> getPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageNum", defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "pageNum", defaultValue = "") String name);

    @GetMapping
    public Result<List<Category>> getAll(@RequestParam(value = "name", defaultValue = "") String name);

    @GetMapping("{id}")
    public Result<Category> getMenu(@PathVariable("id") int id);

    @GetMapping("ids")
    public Result<Stream<Long>> getIds();

    @PostMapping
    public Result<Boolean> edit(@RequestBody Category category);

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids);

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable("id") long id);
}
