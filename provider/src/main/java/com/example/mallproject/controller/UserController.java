package com.example.mallproject.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.common.api.Result;
import com.example.mallproject.common.utils.JwtUtil;
import com.example.mallproject.common.utils.ValidatorUtils;
import com.example.mallproject.entity.dto.UserDTO;
import com.example.mallproject.entity.User;
import com.example.mallproject.service.UserDTOService;
import com.example.mallproject.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserDTOService userDtoService;

    public UserController(UserService userService, UserDTOService userDtoService) {
        this.userService = userService;
        this.userDtoService = userDtoService;
    }

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody UserDTO userDTO) {
        ValidatorUtils.checkNull(userDTO, "user");
        ValidatorUtils.checkNull(userDTO.getUsername(), "username");
        ValidatorUtils.checkNull(userDTO.getPassword(), "password");

        User loginUser = userService.login(userDTO);
        if (loginUser == null) {
            return Result.loginFailed();
        }

        String token = JwtUtil.sign(userDTO.getUsername(),userDTO.getPassword());
        userDtoService.setLoginUserInfo(loginUser, userDTO, token);
        return Result.Success(userDTO, "登录成功");
    }

    @PostMapping("/register")
    public Result<UserDTO> enroll(@RequestBody UserDTO userDTO) {
        ValidatorUtils.checkNull(userDTO, "userDTO");
        ValidatorUtils.checkNull(userDTO.getUsername(), "username");
        ValidatorUtils.checkNull(userDTO.getPassword(), "password");

        userDTO.setRole("USER");
        User loginUser = userService.enroll(userDTO);
        String token = JwtUtil.sign(userDTO.getUsername(),userDTO.getPassword());
        if (token != null) {
            userDtoService.setLoginUserInfo(loginUser, userDTO, token);
            return Result.Success(userDTO, "注册成功");
        } else {
            return Result.registerFailed();
        }
    }

    @GetMapping("/page")
    public Result<Page<User>> pageResult(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                         @RequestParam(value = "username", defaultValue = "") String username) {
        return Result.Success(userService.selectAll(pageNum, pageSize, username));
    }

    @PostMapping
    public Result<Boolean> edit(@RequestBody User user) {
        ValidatorUtils.checkNull(user, "user");
        return Result.Success(userService.saveOrUpdate(user));
    }

    @DeleteMapping(("{id}"))
    public Result<Boolean> delete(@PathVariable long id) {
        return Result.Success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBatchId(@RequestBody List<Long> ids) {
        return Result.Success(userService.removeBatchByIds(ids));
    }

    /**
     *
     * @use 根据token获取登录用户信息
     */
//    @GetMapping("/username/{token}")
//    public Result<User> findOne(@PathVariable String token) {
//        DecodedJWT jwt = JWT.decode(token);
//        String username = jwt.getClaim("username").asString();
//        return Result.Success(userService.getUser(username));
//    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.Success(userService.getOne(queryWrapper));
    }
}
