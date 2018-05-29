package com.example.kuangjiademo.service.user;

import com.example.kuangjiademo.model.User;

/**
 * @author liuyang
 * @since 2018/5/25 16:46
 */
public interface UserService {
    public boolean reg(User user);
    public String login(User user);
    String refresh(String oldToken);
}
