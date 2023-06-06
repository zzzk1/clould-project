package com.example.mallproject.feign;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Order;
import com.example.mallproject.entity.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="provider",path = "/order")
public interface OrderFeignService {
    @GetMapping("/page")
    public Result<Page<Order>> getAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "username", defaultValue = "") String username);

    @PostMapping
    public Result<Boolean> edit(@RequestBody Order order);

    @DeleteMapping("{id}")
    public Result<Boolean> delete(@PathVariable("id") long id);

    @PostMapping("/del/batch")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    @GetMapping("/front/page/{username}")
    public Result<Page<OrderDTO>> getFrontAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "spuName", defaultValue = "") String spuName,
                                              @PathVariable("username") String username);
}
