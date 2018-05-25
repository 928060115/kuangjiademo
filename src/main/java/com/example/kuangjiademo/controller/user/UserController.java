package com.example.kuangjiademo.controller.user;

import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultUtil;
import com.example.kuangjiademo.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        Date createTime = new Date();
        user.setCreareTime(createTime);
        String uid = "ID" + createTime.getTime();
        user.setUid(uid);
        userService.reg(user);
        return ResultUtil.success();
    }
}
