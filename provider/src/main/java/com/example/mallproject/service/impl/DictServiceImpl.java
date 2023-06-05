package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mallproject.entity.Dict;
import com.example.mallproject.service.DictService;
import com.example.mallproject.mapper.DictMapper;
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
 * @since 2023-04-20
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> getAll(String type) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("type", type);
        return dictMapper.selectList(queryWrapper);
    }
}
