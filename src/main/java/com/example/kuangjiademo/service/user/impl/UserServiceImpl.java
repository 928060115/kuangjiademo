package com.example.kuangjiademo.service.user.impl;

import com.example.kuangjiademo.mapper.UserMapper;
import com.example.kuangjiademo.model.User;
import com.example.kuangjiademo.model.UserExample;
import com.example.kuangjiademo.service.user.UserService;
import com.example.kuangjiademo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Date createTime = new Date();
        user.setCreateTime(createTime);
        user.setPassword(MD5Util.md5(user.getPassword()));
        String uid = "ID" + createTime.getTime();
        user.setUid(uid);
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        UserExample example = new UserExample();
        Date lastTime = new Date();
        user.setLastLogin(lastTime);
        UserExample.Criteria criteria = example.createCriteria().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(user.getPassword());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() == 1){
            user = userList.get(0);
            userMapper.updateByPrimaryKey(user);
            return user;
        }
        return null;
    }
}
