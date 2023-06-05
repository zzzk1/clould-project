package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */
public interface CategoryService extends IService<Category> {

    Page<Category> getPage(Integer pageNum, Integer pageSize, String name);

    List<Category> getAll(String name);
}
