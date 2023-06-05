package com.example.mallproject.mapper;

import com.example.mallproject.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
