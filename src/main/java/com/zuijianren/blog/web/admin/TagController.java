package com.zuijianren.blog.web.admin;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Tag;
import com.zuijianren.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("")
    public String tags(PageResult<Tag> pageResult, Model model) {
        pageResult = tagService.listTag(pageResult);
        model.addAttribute("page", pageResult);
        return "admin/tags";
    }

    @GetMapping("/{page}")
    public String tags2(PageResult<Tag> pageResult, Model model) {
        pageResult = tagService.listTag(pageResult);
        model.addAttribute("page", pageResult);
        return "admin/tags";
    }

    @GetMapping("/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("")
    public String post( Tag tag,  RedirectAttributes attributes){
        if(tag.getName() != null && tag.getName() != ""){
            Date date = new Date();
            long time = date.getTime();
            tag.setId(time);
            String s = tagService.saveTag(tag);
            attributes.addFlashAttribute("message", s);
        }else{
            attributes.addFlashAttribute("message", "添加失败, 标签名不能为空");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/{id}")
    public String editPost( Tag tag,@PathVariable("id") Long id,  RedirectAttributes attributes){
        if(tag.getName() != null && tag.getName() != ""){
            String s = tagService.updateTag(id, tag);
            attributes.addFlashAttribute("message", s);
        }else{
            attributes.addFlashAttribute("message", "更新失败, 标签名不能为空");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }


}
