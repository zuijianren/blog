package com.zuijianren.blog.service;

import com.zuijianren.blog.mapper.CommentMapper;
import com.zuijianren.blog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    @Value("${comment.avatar}")
    private String avatar;

    //保存 comment
    @Override
    public void save(Comment comment){
        //进行默认值的处理
        Date date = new Date();
        long time = date.getTime();
        comment.setId(time);
        comment.setAvatar(avatar);
        if(comment.getParentCommentId() == -1){
            //如果是一级评论，则将 topCommentId 设为自己的id
            comment.setTopCommentId(comment.getId());
        }else{
            //否则使用上一级的topCommentId作为自己的 topCommentId
            comment.setTopCommentId(commentMapper.findCommentById(comment.getParentCommentId()).getTopCommentId());
        }
        commentMapper.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentMapper.updateContentCommentById("该评论已被博主删除", id);
    }

    @Override
    public List<Comment> listComments(Long bId) {
        return commentMapper.listComment(bId);
    }

    @Override
    public List<Comment> listCommentReplies(Long id) {
        List<Comment> comments = listComments(id);
        for (Comment comment : comments) {
            //查询所有的对应评论下的回复
            List<Comment> replies = commentMapper.listReplies(comment);
            for (Comment reply : replies) {
                //填充回复的父评论，以便进行展示
                reply.setParentComment(commentMapper.findCommentById(reply.getParentCommentId()));
            }
            comment.setReplyComments(replies);
        }
        return comments;
    }
}
