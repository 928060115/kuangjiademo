package com.example.kuangjiademo.service.user;

import com.example.kuangjiademo.model.User;

/**
 * @author liuyang
 * @since 2018/5/25 16:46
 */
public interface UserService {
    public void reg(User user);
    public User login(User user);
}
