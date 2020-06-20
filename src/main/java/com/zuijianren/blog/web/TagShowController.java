package com.zuijianren.blog.web;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.pojo.Tag;
import com.zuijianren.blog.pojo.Type;
import com.zuijianren.blog.service.BlogService;
import com.zuijianren.blog.service.BlogTagService;
import com.zuijianren.blog.service.TagService;
import com.zuijianren.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("")
    public String type(PageResult<Blog> pageResult, Model model){
        //获取所有types
        List<Tag> tags = tagService.listTagAll();
        model.addAttribute("tags", tags);
        //获取对应 tags 下的所有blog，默认为第一个tag
        long id = tags.get(0).getId();
        model.addAttribute("blogs", blogTagService.listBlogByTId(pageResult, id));
        model.addAttribute("activeTagId", id);
        return "tag";
    }

    @GetMapping("/{id}")
    public String typeId(@PathVariable("id")long id, PageResult<Blog> pageResult, Model model){
        //获取所有types
        List<Tag> tags = tagService.listTagAll();
        model.addAttribute("tags", tags);
        model.addAttribute("blogs", blogTagService.listBlogByTId(pageResult, id));
        model.addAttribute("activeTagId", id);
        return "tag";
    }
}
