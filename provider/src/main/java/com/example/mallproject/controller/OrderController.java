package com.example.mallproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.common.api.Result;
import com.example.mallproject.entity.Order;
import com.example.mallproject.entity.dto.OrderDTO;
import com.example.mallproject.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/page")
    public Result<Page<Order>> getAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "username", defaultValue = "") String username) {
        return Result.Success(orderService.selectAll(pageNum, pageSize, username));
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody Order order) {
        return Result.Success(orderService.saveOrUpdate(order));
    }

    @DeleteMapping("{id}")
    public Result<Boolean> delete(@PathVariable long id) {
        return Result.Success(orderService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.Success(orderService.removeBatchByIds(ids));
    }

    @GetMapping("/front/page/{username}")
    public Result<Page<OrderDTO>> getFrontAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "spuName", defaultValue = "") String spuName,
                                              @PathVariable String username) {
        return Result.Success(orderService.getPageBySpuName(pageNum, pageSize, spuName, username));
    }
}
