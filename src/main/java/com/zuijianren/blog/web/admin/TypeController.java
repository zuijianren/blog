package com.zuijianren.blog.web.admin;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Type;
import com.zuijianren.blog.service.TypeService;
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
@RequestMapping("/admin/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("")
    public String types(PageResult<Type> pageResult, Model model) {
        pageResult = typeService.listType(pageResult);
        model.addAttribute("page", pageResult);
        return "admin/types";
    }

    @GetMapping("/{page}")
    public String types2(PageResult<Type> pageResult, Model model) {
        pageResult = typeService.listType(pageResult);
        model.addAttribute("page", pageResult);
        return "admin/types";
    }

    @GetMapping("/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("")
    public String post( Type type,  RedirectAttributes attributes){
        if(type.getName() != null && type.getName() != ""){
            Date date = new Date();
            long time = date.getTime();
            type.setId(time);
            String s = typeService.saveType(type);
            attributes.addFlashAttribute("message", s);
        }else{
            attributes.addFlashAttribute("message", "添加失败, 分类名不能为空");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/{id}")
    public String editPost( Type type,@PathVariable("id") Long id,  RedirectAttributes attributes){
        if(type.getName() != null && type.getName() != ""){
            String s = typeService.updateType(id, type);
            attributes.addFlashAttribute("message", s);
        }else{
            attributes.addFlashAttribute("message", "更新失败, 分类名不能为空");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }


}
