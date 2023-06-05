package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
public interface MenuService extends IService<Menu> {
    Page<Menu>selectAll(int pageNum, int pageSize, String name);
    List<Menu> getAll(String menuName);
}
