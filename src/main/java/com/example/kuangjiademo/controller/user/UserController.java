package com.example.kuangjiademo.controller.user;

import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyang
 * @since 2018/5/24 10:14
 */
@Api(tags = {"UserController"},description = "用户接口类")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public Result reg(User user){
        return ResultUtil.success("用户接口index");
    }
}
