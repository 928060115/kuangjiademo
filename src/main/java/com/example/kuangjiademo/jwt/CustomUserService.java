package com.example.kuangjiademo.jwt;

import com.example.kuangjiademo.mapper.UserMapper;
import com.example.kuangjiademo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author liuyang
 * @since 2018/5/29 15:36
 */
@Slf4j
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        log.debug("用户名：{}",username);
        return user;
    }
}
