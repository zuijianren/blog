package com.zuijianren.blog.web;

import com.zuijianren.blog.mapper.BlogMapper;
import com.zuijianren.blog.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping("/test1")
    public List<String> test1(){
        return blogMapper.listDate();
    }
    @RequestMapping("/test2")
    public List<Blog> test2(){
        return blogMapper.listPublishedByDate("2020.06");
    }
}
