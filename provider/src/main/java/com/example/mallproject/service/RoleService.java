package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
public interface RoleService extends IService<Role> {
    Page<Role> selectAll(int pageNum, int pageSize, String roleName);

    Boolean updateRoleMenuInfo(int rid, List<Integer> menusId);

    List<Integer> getMenusById(int rid);
}
