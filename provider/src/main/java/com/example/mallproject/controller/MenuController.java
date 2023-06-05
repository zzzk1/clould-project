package com.example.mallproject.controller;


import com.example.mallproject.common.api.Result;
import com.example.mallproject.common.utils.ValidatorUtils;
import com.example.mallproject.entity.Dict;
import com.example.mallproject.entity.Menu;

import com.example.mallproject.service.DictService;
import com.example.mallproject.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * @return 父节点
     */
    @GetMapping
    public Result<List<Menu>> getAll(@RequestParam(defaultValue = "") String name) {
        List<Menu> list = menuService.getAll(name);
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == 0).collect(Collectors.toList());
        for (Menu menu : parentNode) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return Result.Success(parentNode);
    }

    @GetMapping("{id}")
    public Result<Menu> getMenu(@PathVariable int id) {
        return Result.Success(menuService.getById(id));
    }

    @GetMapping("ids")
    public Result<Stream<Integer>> getMenuIds() {
        return Result.Success(menuService.list().stream().map(Menu::getId));
    }

    @Autowired
    private DictService dictService;
    @GetMapping("/icons")
    public Result<List<Dict>> getAllDist(@RequestParam(defaultValue = "icon") String type) {
        return Result.Success(dictService.getAll(type));
    }

    //新增或修改
    @PostMapping
    public Result<Boolean> edit(@RequestBody Menu menu) {
        ValidatorUtils.checkNull(menu, "menu");
        return Result.Success(menuService.saveOrUpdate(menu));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return Result.Success(menuService.removeBatchByIds(ids));
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return Result.Success(menuService.removeById(id));
    }

}
