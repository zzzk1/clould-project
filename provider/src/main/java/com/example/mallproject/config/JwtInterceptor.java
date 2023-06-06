package com.example.mallproject.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import utils.JwtUtil;
import com.example.mallproject.entity.User;
import com.example.mallproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzzk1
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (token != null) {
            String username = JwtUtil.getUserNameByToken(request);
            User loginUser = userService.getOne(new QueryWrapper<User>().eq("username", username));
            // 这边拿到的 用户名 应该去数据库查询获得密码，简略，步骤在service直接获取密码
            boolean result = JwtUtil.verify(token, username, loginUser.getPassword());
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        return false;
    }
}
