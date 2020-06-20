package com.zuijianren.blog.mapper;

import com.zuijianren.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String passssword);
}
