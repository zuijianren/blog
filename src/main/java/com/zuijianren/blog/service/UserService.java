package com.zuijianren.blog.service;

import com.zuijianren.blog.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User checkUser(String username, String password);
}
