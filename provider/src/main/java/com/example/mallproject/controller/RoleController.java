package com.example.mallproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.common.api.Result;
import com.example.mallproject.common.utils.ValidatorUtils;
import com.example.mallproject.entity.Role;
import com.example.mallproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result<List<Role>> getAll() {
        return Result.Success(roleService.list());
    }
    //分页模糊查询
    @GetMapping("/page")
    public Result<Page<Role>> pageResult(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                         @RequestParam(value = "name", defaultValue = "") String name) {
        return Result.Success(roleService.selectAll(pageNum, pageSize, name));
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Role role) {
        ValidatorUtils.checkNull(role, "role");
        return Result.Success(roleService.saveOrUpdate(role));
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return Result.Success(roleService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return Result.Success(roleService.removeBatchByIds(ids));
    }

    @PostMapping("/roleMenu/{rid}")
    public Result<Boolean> roleMenu(@PathVariable int rid, @RequestBody List<Integer> menusId) {
        return Result.Success(roleService.updateRoleMenuInfo(rid,menusId ));
    }

    @GetMapping("/roleMenu/{rid}")
    public Result<List<Integer>> getRoleMenu(@PathVariable int rid) {
        return Result.Success(roleService.getMenusById(rid));
    }
}
