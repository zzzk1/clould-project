package com.example.mallproject.feign;

import api.Result;
import com.example.mallproject.entity.Dict;
import com.example.mallproject.entity.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.stream.Stream;

@FeignClient(value="provider",path = "/menu")
public interface MenuFeignService {
    @GetMapping
    public Result<List<Menu>> getAll(@RequestParam(value = "name", defaultValue = "") String name);

    @GetMapping("{id}")
    public Result<Menu> getMenu(@PathVariable("id") int id);

    @GetMapping("ids")
    public Result<Stream<Integer>> getMenuIds();

    @GetMapping("/icons")
    public Result<List<Dict>> getAllDist(@RequestParam(value = "type", defaultValue = "icon") String type);

    @PostMapping
    public Result<Boolean> edit(@RequestBody Menu menu);

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids);

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable("id") long id);
}
