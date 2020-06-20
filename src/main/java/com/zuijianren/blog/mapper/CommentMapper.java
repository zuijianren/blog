package com.zuijianren.blog.mapper;

import com.zuijianren.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    public void save(@Param("comment")Comment comment);
    public void updateContentCommentById(@Param("s") String s, @Param("id") Long id);
    //通过blog的id查询所有parentComment的id为-1的评论
    public List<Comment> listComment(@Param("bId") long bId);
    public List<Comment> listReplies(@Param("comment") Comment comment);
    public Comment findCommentById(@Param("id") long id);
}
