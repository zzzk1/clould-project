package com.example.mallproject.service.impl;

import com.example.mallproject.entity.RoleMenu;
import com.example.mallproject.service.RoleMenuService;
import com.example.mallproject.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int deleteById(int rid) {
        return roleMenuMapper.deleteById(rid);
    }

    @Override
    public List<Integer> getMenusById(int rid) {
        return roleMenuMapper.getMenusById(rid);
    }

}
