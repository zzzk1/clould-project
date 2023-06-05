package com.example.mallproject.entity.dto;

import com.example.mallproject.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String nickname;
    private String password;
    private String avatarUrl;
    private String token;
    private List<Menu> menus;
    private String role;
}
