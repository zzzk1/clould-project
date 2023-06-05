package com.example.mallproject.service;

import com.example.mallproject.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-20
 */
public interface DictService extends IService<Dict> {
    List<Dict> getAll(String type);
}
