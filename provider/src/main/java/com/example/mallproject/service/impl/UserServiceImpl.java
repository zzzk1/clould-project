package com.example.mallproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.dto.UserDTO;
import com.example.mallproject.entity.User;
import com.example.mallproject.service.UserService;
import com.example.mallproject.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> selectAll(int curr, int size, String name) {
        Page<User> page = new Page<>(curr,size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().like("username", name);
        return userMapper.selectPage(page, queryWrapper);
    }


    @Autowired
    private UserService userService;
    @Override
    public User login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        return userService.getOne(queryWrapper);
    }

    @Override
    public User enroll(UserDTO userDTO) {
        if (userService.getOne(new QueryWrapper<User>().eq("username", userDTO.getUsername())) != null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        if (userService.save(user)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userService.getOne(queryWrapper);
    }
}
