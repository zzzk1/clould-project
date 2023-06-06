package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Order;
import com.example.mallproject.entity.dto.OrderDTO;
import com.example.mallproject.feign.OrderFeignService;
import com.example.mallproject.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("order")
public class OrderFeignController {
    @Autowired
    private OrderFeignService orderService;

    @GetMapping("/page")
    public Result<Page<Order>> getAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "username", defaultValue = "") String username) {
        return orderService.getAll(pageNum, pageSize, username);
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Order order) {
        return orderService.edit(order);
    }

    @DeleteMapping("{id}")
    public Result<Boolean> delete(@PathVariable long id) {
        return orderService.delete(id);
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return orderService.deleteByIds(ids);
    }

    @GetMapping("/front/page/{username}")
    public Result<Page<OrderDTO>> getFrontAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "spuName", defaultValue = "") String spuName,
                                              @PathVariable String username) {
        return orderService.getFrontAll(pageNum, pageSize, spuName, username);
    }
}
