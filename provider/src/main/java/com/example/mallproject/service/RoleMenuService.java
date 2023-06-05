package com.example.mallproject.service;

import com.example.mallproject.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
public interface RoleMenuService extends IService<RoleMenu> {

    int deleteById(int rid);

    List<Integer> getMenusById(int rid);
}
