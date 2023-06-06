package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Role;
import com.example.mallproject.feign.RoleFeignService;
import com.example.mallproject.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ValidatorUtils;

import java.util.List;

/**
 * @author zzzk1
 */
@RestController
public class RoleFeignController {
    @Autowired
    private RoleFeignService roleService;

    @GetMapping
    public Result<List<Role>> getAll() {
        return roleService.getAll();
    }
    //分页模糊查询
    @GetMapping("/page")
    public Result<Page<Role>> pageResult(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                         @RequestParam(value = "name", defaultValue = "") String name) {
        return roleService.pageResult(pageNum, pageSize, name);
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Role role) {
        ValidatorUtils.checkNull(role, "role");
        return roleService.edit(role);
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return roleService.delete(id);
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return roleService.deleteBatchId(ids);
    }

    @PostMapping("/roleMenu/{rid}")
    public Result<Boolean> roleMenu(@PathVariable int rid, @RequestBody List<Integer> menusId) {
        return roleService.roleMenu(rid, menusId);
    }

    @GetMapping("/roleMenu/{rid}")
    public Result<List<Integer>> getRoleMenu(@PathVariable int rid) {
        return roleService.getRoleMenu(rid);
    }
}
