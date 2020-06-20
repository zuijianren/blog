package com.zuijianren.blog.service;

import com.zuijianren.blog.mapper.UserMapper;
import com.zuijianren.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User checkUser(String username, String password){
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
