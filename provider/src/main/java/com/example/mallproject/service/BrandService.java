package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mallproject.entity.SpuInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */
public interface BrandService extends IService<Brand> {
    Page<SpuInfo> getSpuInfo(int id, int curr, int size);
}
