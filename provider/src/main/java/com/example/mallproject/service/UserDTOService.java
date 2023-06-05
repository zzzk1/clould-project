package com.example.mallproject.service;

import com.example.mallproject.entity.dto.UserDTO;
import com.example.mallproject.entity.User;

import java.util.List;

/**
 * @author zzzk1
 */
public interface UserDTOService {

    List<Integer> getLoginUserMenuIds(String role);
    void setLoginUserInfo(User user, UserDTO userDTO, String token);
}
