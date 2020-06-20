package com.zuijianren.blog.web;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.pojo.Type;
import com.zuijianren.blog.service.BlogService;
import com.zuijianren.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String type(PageResult<Blog> pageResult, Model model){
        //获取所有types
        List<Type> types = typeService.listTypeAll();
        model.addAttribute("types", types);
        //获取对应types下的所有blog，默认为第一个type
        long id = types.get(0).getId();
        model.addAttribute("blogs", blogService.listBlogByTId(pageResult, id));
        model.addAttribute("activeTypeId", id);
        return "type";
    }

    @GetMapping("/{id}")
    public String typeId(@PathVariable("id")long id, PageResult<Blog> pageResult, Model model){
        //获取所有types
        List<Type> types = typeService.listTypeAll();
        model.addAttribute("types", types);
        model.addAttribute("blogs", blogService.listBlogByTId(pageResult, id));
        model.addAttribute("activeTypeId", id);
        return "type";
    }
}
