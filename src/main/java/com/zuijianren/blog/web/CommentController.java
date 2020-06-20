package com.zuijianren.blog.web;

import com.zuijianren.blog.pojo.Comment;
import com.zuijianren.blog.pojo.User;
import com.zuijianren.blog.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //保存评论
    @PostMapping("")
    public String post(Comment comment, HttpSession session){
        //判断当前用户是否是管理者
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAdmin(true);
        }else{
            comment.setAdmin(false);
        }
        commentService.save(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }

    //删除评论
    @PostMapping("/delete")
    public String delete(@RequestParam("blogId") long bId, @RequestParam("commentId") long  cId ){
        commentService.deleteCommentById(cId);
        return "redirect:/comments/" + bId;
    }

    //重新加载评论
    @GetMapping("/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentReplies(blogId));
        return "blog :: commentList";
    }


}
