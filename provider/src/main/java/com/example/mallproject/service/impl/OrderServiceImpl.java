package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Order;
import com.example.mallproject.entity.dto.OrderDTO;
import com.example.mallproject.service.OrderService;
import com.example.mallproject.service.SpuInfoService;
import com.example.mallproject.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override

    public Page<Order> selectAll(int pageNum, int pageSize, String username) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>().like("username", username);
        return orderMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Autowired
    private SpuInfoService spuInfoService;
    @Override
    public Page<OrderDTO> getPageBySpuName(int pageNum, int pageSize, String spuName, String username) {
        List<Long> ids = spuInfoService.getIds(spuName);
        List<OrderDTO> orderDTOS = orderMapper.selectOrderDTO(ids, username);
        Page<OrderDTO> page = new Page<>(pageNum, pageSize);
        page.setRecords(orderDTOS);
        page.setSize(orderDTOS.size());
        return page;
    }

}
