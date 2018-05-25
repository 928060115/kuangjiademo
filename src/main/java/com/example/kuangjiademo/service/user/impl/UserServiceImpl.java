package com.example.kuangjiademo.service.user.impl;

import com.example.kuangjiademo.mapper.UserMapper;
import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyang
 * @since 2018/5/25 16:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        userMapper.insert(user);
    }
}
