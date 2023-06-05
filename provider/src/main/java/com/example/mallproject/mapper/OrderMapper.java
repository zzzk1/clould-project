package com.example.mallproject.mapper;

import com.example.mallproject.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mallproject.entity.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<OrderDTO> selectOrderDTO(@Param("spuIds") List<Long> spuIds,@Param("username") String username);
}
