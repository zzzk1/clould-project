package com.example.mallproject.controller;

import api.Result;
import com.example.mallproject.entity.Dict;
import com.example.mallproject.entity.Menu;
import com.example.mallproject.feign.MenuFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("menu")
public class MenuFeignController {

    @Autowired
    private MenuFeignService menuService;

    @GetMapping
    public Result<List<Menu>> getAll(@RequestParam(defaultValue = "") String name) {
       return menuService.getAll(name);
    }

    @GetMapping("{id}")
    public Result<Menu> getMenu(@PathVariable int id) {
        return menuService.getMenu(id);
    }

    @GetMapping("ids")
    public Result<Stream<Integer>> getMenuIds() {
        return menuService.getMenuIds();
    }

    @GetMapping("/icons")
    public Result<List<Dict>> getAllDist(@RequestParam(defaultValue = "icon") String type) {
        return menuService.getAllDist(type);
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Menu menu) {
        return menuService.edit(menu);
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return menuService.deleteBatchId(ids);
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return menuService.delete(id);
    }
}
