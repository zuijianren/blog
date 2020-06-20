package com.zuijianren.blog.web;

import com.zuijianren.blog.exception.BlogNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/")
    public String index(PageResult pageResult, Model model){
       /* String blog = null;
        if(blog == null){
            throw new BlogNotFoundException("博客未找到");
        }*/
        //按createTime排序的已发布的全部的博客
        model.addAttribute("pages", blogService.listBlogPublished(pageResult));
        //最多的6个type,可以通过mapper.xml文件更改
        model.addAttribute("types",typeService.listTypeTop());
        //最多的10个tag
        model.addAttribute("tags",tagService.listTagTop());
        //最新推荐的已发布的博客8篇
        model.addAttribute("blogs", blogService.listBlogRecommendTop());
        return "index";
    }

    @GetMapping("/index")
    public String index2(){
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String search(PageResult pageResult, @RequestParam("query") String query, Model model){
        //查询title或者description中包含query字符串的blog
        model.addAttribute("pages", blogService.listBlogPublishedSearch(pageResult, query));
        model.addAttribute("query", query);
        return "search";
    }



    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") long id , Model model ){
        model.addAttribute("blog", blogService.getAndConvertBlog(id));
        model.addAttribute("tagIds", blogTagService.getTagsByBId(id));
        return "blog";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
