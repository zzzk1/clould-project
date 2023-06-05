package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Category;
import com.example.mallproject.service.CategoryService;
import com.example.mallproject.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryService categoryService;


    @Override
    public Page<Category> getPage(Integer pageNum, Integer pageSize, String name) {
        Page<Category> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.like("name", name);
        return categoryService.page(page, categoryQueryWrapper);
    }

    @Override
    public List<Category> getAll(String name) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.like("name", name);
        List<Category> categories = categoryService.list(categoryQueryWrapper);
        return mergeCategory(categories);
    }

    private List<Category> mergeCategory(List<Category> categories) {
        HashMap<Long, Category> hashMap = new HashMap<>();
        for (Category category : categories) {
            hashMap.put(category.getId(), category);
        }

        List<Category>mergeCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.getPid() == 0) {
                mergeCategories.add(category);
            } else {
                Category parent = hashMap.get(category.getPid());
                parent.getChildren().add(category);
            }
        }
        return mergeCategories;
    }
}
