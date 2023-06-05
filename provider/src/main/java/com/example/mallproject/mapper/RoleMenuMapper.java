package com.example.mallproject.mapper;

import com.example.mallproject.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Delete("delete from sys_role_menu where role_id = #{rid}")
    int deleteById( int rid);

    @Select("select menu_id from sys_role_menu where role_id = #{rid}")
    List<Integer> getMenusById(int rid);
}
