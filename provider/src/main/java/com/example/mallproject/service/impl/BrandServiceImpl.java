package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.mallproject.service.BrandService;
import com.example.mallproject.service.SpuInfoService;
import com.example.mallproject.entity.Brand;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.mapper.BrandMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-12
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    private SpuInfoService spuInfoService;

    @Override
    public Page<SpuInfo> getSpuInfo(int id, int curr, int size) {
        return spuInfoService.getSpusByBrandId(id, curr, size);
    }
}
