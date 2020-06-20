package com.zuijianren.blog.service;

import com.zuijianren.blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    //保存 comment
    public void save(Comment comment);
    public void deleteCommentById(Long id);
    //获取blog对应的所有非回复的评论
    public List<Comment> listComments(Long bId);
    //获取blog对应的所有回复的评论，即非一级评论，父评论id不为-1
    public List<Comment> listCommentReplies(Long id);

}
