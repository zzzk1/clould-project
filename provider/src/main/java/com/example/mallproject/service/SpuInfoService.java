package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-16
 */
public interface SpuInfoService extends IService<SpuInfo> {
    Page<SpuInfo> getSpusByBrandId(int brandId, int curr, int size);
    List<Long> getIds(String spuName);

}
