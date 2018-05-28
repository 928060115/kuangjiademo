package com.example.kuangjiademo.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultUtil;
import com.example.kuangjiademo.service.user.UserService;
import com.example.kuangjiademo.utils.MD5Util;
import com.example.kuangjiademo.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author liuyang
 * @since 2018/5/24 10:14
 */
@Api(tags = {"UserController"},description = "用户接口类")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public Result reg(User user){
        userService.reg(user);
        return ResultUtil.success();
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(User user, String code, HttpSession session){
        String resultVerifyCode = Utils.checkVerify(session,code);
        if ("true".equals(resultVerifyCode)){
            user.setPassword(MD5Util.md5(user.getPassword()));
            user = userService.login(user);
            if (user != null){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(user);
                jsonObject.remove("password");
                return  ResultUtil.success(jsonObject);
            }
            return ResultUtil.error("用户名或密码错误");
        }else {
            return  ResultUtil.error(resultVerifyCode);
        }

    }
}
