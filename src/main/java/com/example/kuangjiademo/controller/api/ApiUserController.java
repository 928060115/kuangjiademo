package com.example.kuangjiademo.controller.api;

import com.example.kuangjiademo.jwt.JwtAuthenticationResponse;
import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultUtil;
import com.example.kuangjiademo.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuyang
 * @since 2018/5/30 14:17
 */
@Api(tags = {"UserController"}, description = "用户接口类")
@RestController
@RequestMapping(value = "/Api")
@Slf4j
public class ApiUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/public/reg", method = RequestMethod.POST)
    public Result reg(User user) {

        boolean result = userService.reg(user);
        if (result) {
            return ResultUtil.success("注册成功");
        }
        return ResultUtil.error("用户名已被注册");
    }

    @ApiOperation(value = "用户登录Api")
    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    public Result login(User user) {
        final String token = userService.login(user);
        if (token != null) {
            return ResultUtil.success(new JwtAuthenticationResponse(token));
        }
        return ResultUtil.error("用户名或密码错误");
    }

    @ApiOperation(value = "更新token")
    @RequestMapping(value = "/public/refresh", method = RequestMethod.POST)
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = userService.refresh(token);
        if (refreshedToken == null) {
            return ResultUtil.error("更新失败");
        } else {
            return ResultUtil.success(new JwtAuthenticationResponse(refreshedToken));
        }
    }
}
