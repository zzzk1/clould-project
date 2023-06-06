package com.example.mallproject.feign;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Role;
import com.example.mallproject.entity.SpuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import utils.ValidatorUtils;

import java.util.List;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/role")
public interface RoleFeignService {

    @GetMapping
    public Result<List<Role>> getAll();
    //分页模糊查询
    @GetMapping("/page")
    public Result<Page<Role>> pageResult(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                         @RequestParam(value = "name", defaultValue = "") String name);

    @PostMapping
    public Result<Boolean> edit(@RequestBody Role role);

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable("rid") long id);

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids);

    @PostMapping("/roleMenu/{rid}")
    public Result<Boolean> roleMenu(@PathVariable("rid") int rid, @RequestBody List<Integer> menusId);

    @GetMapping("/roleMenu/{rid}")
    public Result<List<Integer>> getRoleMenu(@PathVariable("rid") int rid);
}
