package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mallproject.entity.dto.UserDTO;
import com.example.mallproject.entity.Menu;
import com.example.mallproject.entity.Role;
import com.example.mallproject.entity.User;
import com.example.mallproject.service.MenuService;
import com.example.mallproject.service.RoleMenuService;
import com.example.mallproject.service.RoleService;
import com.example.mallproject.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDTOServiceImpl implements UserDTOService {

    @Autowired
    private RoleService roleService;
    //通过用户表中的role字段查找roleId
    private int getRoleId(String role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>().eq("flag", role);
        queryWrapper.select("id");
        Role selectRole = roleService.getOne(queryWrapper);
        return selectRole.getId();
    }

    @Autowired
    private RoleMenuService roleMenuService;
    @Override
    public List<Integer> getLoginUserMenuIds(String role) {
        int roleId = getRoleId(role);
        return roleMenuService.getMenusById(roleId);
    }

    @Override
    public void setLoginUserInfo(User user, UserDTO userDTO, String token) {
        userDTO.setUsername(user.getUsername());
        userDTO.setNickname(user.getNickname());
        userDTO.setPassword(null);
        userDTO.setToken(token);
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setRole(user.getRole());
        userDTO.setMenus(getAllMenu(userDTO.getRole()));
    }

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDTOService userDtoService;
    private List<Menu> getAllMenu(String role) {
        List<Integer> ids = userDtoService.getLoginUserMenuIds(role);
        List<Menu> menus = menuService.listByIds(ids);
        return mergedMenus(menus);
    }

    private List<Menu> mergedMenus(List<Menu> menus) {
        //将子菜单分入父菜单
        List<Menu> parentNode = menus.stream().filter(menu -> menu.getPid() == 0).collect(Collectors.toList());
        for (Menu menu : parentNode) {
            menu.setChildren(menus.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        List<Menu> mergedMenu = new ArrayList<>();
        //获取父菜单
        for (Menu menu : menus) {
            if (menu.getPid() == 0) {
                mergedMenu.add(menu);
            }
        }
        return mergedMenu;
    }
}
