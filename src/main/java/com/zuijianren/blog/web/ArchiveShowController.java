package com.zuijianren.blog.web;

import com.zuijianren.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 归档页面
 */
@Controller
@RequestMapping("/archive")
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;



    @GetMapping("")
    public String archive(Model model){
        model.addAttribute("DBs", blogService.MapDateBlog());
        model.addAttribute("BlogCount", blogService.countPublished());
        return "archive";
    }
}
