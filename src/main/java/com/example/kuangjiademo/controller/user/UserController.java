package com.example.kuangjiademo.controller.user;

import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultUtil;
import com.example.kuangjiademo.service.user.UserService;
import com.example.kuangjiademo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author liuyang
 * @since 2018/5/24 10:14
 */
@RestController
@Slf4j
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/public/reg", method = RequestMethod.POST)
    public Result reg(User user) {

        boolean result = userService.reg(user);
        if (result) {
            return ResultUtil.success("注册成功");
        }
        return ResultUtil.error("用户名已被注册");
    }

    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    public Result login(User user, String code, HttpSession session) {
        String resultVerifyCode = Utils.checkVerify(session, code);
        log.debug("session.getAttribute(\"Authorization\"): {}",session.getAttribute("Authorization"));
        if ("true".equals(resultVerifyCode)) {
             String token = userService.login(user);
            if (token != null) {
                if (session.isNew() || session.getAttribute("Authorization") == null) {
                    log.debug("Successfully creates a session ，the id of session ：{}", session.getId());
                    session.setAttribute("Authorization", token);
                } else {
                    token = userService.refresh(session.getAttribute("Authorization").toString());
                    session.setAttribute("Authorization", token);
                    log.info("Successfully update a session ，the id of session ：{}", session.getId());
                }
                return ResultUtil.success();
            }
            return ResultUtil.error("用户名或密码错误");
        } else {
            return ResultUtil.error(resultVerifyCode);
        }

    }
}
