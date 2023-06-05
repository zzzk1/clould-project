package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.service.SpuInfoService;
import com.example.mallproject.mapper.SpuInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-16
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {
    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Override
    public Page<SpuInfo> getSpusByBrandId(int brandId, int curr, int size) {
        Page<SpuInfo> page = new Page<>(curr, size);
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        return spuInfoMapper.selectPage(page, queryWrapper.select("id", "spu_name"));
    }

    @Autowired
    private SpuInfoService spuInfoService;
    @Override
    public List<Long> getIds(String spuName) {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("spu_name", spuName);
        List<SpuInfo> spuInfos = spuInfoService.list(queryWrapper);
        List<Long> ids = new ArrayList<>();
        for (SpuInfo spuInfo : spuInfos) {
            ids.add(spuInfo.getId());
        }
        return ids;
    }
}
