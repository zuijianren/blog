package com.zuijianren.blog.web.admin;

import com.zuijianren.blog.mapper.BlogTagMapper;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.model.BlogRequest;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Tag;
import com.zuijianren.blog.service.BlogService;
import com.zuijianren.blog.service.BlogTagService;
import com.zuijianren.blog.service.TagService;
import com.zuijianren.blog.service.TypeService;
import com.zuijianren.blog.util.TagIdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;


    //加载blog的列表
    @GetMapping("")
    public String blogs(PageResult<Blog> pageResult, Model model){
        //将所有分类放入model，方便展示
        model.addAttribute("types", typeService.getAllTypes());
        //获取Blog的list进行展示
        model.addAttribute("page",  blogService.listBlog(pageResult));
        return "admin/blogs";
    }

    //新增和更新blog
    @PostMapping("")
    public String post(Blog blog, String tagIds, RedirectAttributes attributes){
        //如果id为0，则是新增blog
        //否则为更新blog
        String s;
        if(blog.getId() == 0){
            //新增
            s = blogService.saveBlog(blog, tagIds);
        }else{
            //更新
            s = blogService.updateBlog(blog, tagIds);
        }
        attributes.addFlashAttribute("message", s);
        return "redirect:/admin/blogs";
    }

    //根据条件查询，仅更新部分表格
    @RequestMapping("/search")
    public String search(PageResult<Blog> pageResult, BlogRequest blogRequest, Model model){
        /*//将所有分类放入model，方便展示
        model.addAttribute("types", typeService.getAllTypes());*/

        //获取Blog的list进行展示
        model.addAttribute("page",  blogService.listBlogSearch(pageResult, blogRequest));
        return "admin/blogs :: blogList";
    }

    //跳转新增页面
    @GetMapping("/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    //删除blog
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, RedirectAttributes attributes){
        attributes.addFlashAttribute("message", blogService.deleteBlog(id));
        return "redirect:/admin/blogs";
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        //通过id查找blog，将数据回显到input页面
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        String tagsByBId = blogTagService.getTagsIdStringByBId(id);
        model.addAttribute("tagIds", tagsByBId);
        return "admin/blogs-input";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
    }

}
