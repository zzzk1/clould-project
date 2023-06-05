package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Menu;
import com.example.mallproject.entity.Role;
import com.example.mallproject.entity.RoleMenu;
import com.example.mallproject.service.MenuService;
import com.example.mallproject.service.RoleMenuService;
import com.example.mallproject.service.RoleService;
import com.example.mallproject.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> selectAll(int pageNum, int pageSize, String roleName) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>().like("name", roleName);
        return roleMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * @use 更新角色与菜单的关系
     */
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;
    @Override
    public Boolean updateRoleMenuInfo(int rid, List<Integer> menusId) {
        //清除所有绑定,重新绑定
        roleMenuService.deleteById(rid);
        HashSet<Integer> set = new HashSet<>(menusId);
        List<Menu> menus = menuService.listByIds(menusId);
        for (Menu menu : menus) {
        //菜单id中的菜单父菜单不存在时,为其新建一个父菜单
            if (!set.contains(menu.getPid()) && menu.getPid() != 0) {
                set.add(menu.getPid());
            }
        }

        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Integer menuId : set) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(rid);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }
        return roleMenuService.saveOrUpdateBatch(roleMenus);
    }

    @Override
    public List<Integer> getMenusById(int rid) {
        return roleMenuService.getMenusById(rid);
    }

}
