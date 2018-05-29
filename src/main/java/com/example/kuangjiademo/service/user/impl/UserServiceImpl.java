package com.example.kuangjiademo.service.user.impl;

import com.example.kuangjiademo.jwt.JwtTokenUtil;
import com.example.kuangjiademo.mapper.UserMapper;
import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.model.UserExample;
import com.example.kuangjiademo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liuyang
 * @since 2018/5/25 16:47
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public UserServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    @Override
    public boolean reg(User user) {
        final String userName = user.getUserName();
        if (userMapper.findByUsername(userName)!=null){
            return false;
        }
        Date createTime = new Date();
        user.setCreateTime(createTime);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        String uid = "ID" + createTime.getTime();
        user.setUid(uid);
        userMapper.insert(user);
        return true;
    }

    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserExample example = new UserExample();
        Date lastTime = new Date();
        user.setLastLogin(lastTime);
        UserExample.Criteria criteria = example.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        if (userList.size() == 1  && encoder.matches(user.getPassword(),userList.get(0).getPassword())){
            user = userList.get(0);
            userMapper.updateByPrimaryKey(user);
            return token;
        }
        return null;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(userName);
        if (jwtTokenUtil.canTokenBeRefreshed(token,user.getLastLogin())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
