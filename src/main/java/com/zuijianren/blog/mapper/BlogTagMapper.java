package com.zuijianren.blog.mapper;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogTagMapper {
    public int save(@Param("bId") long bId, @Param("tId") long tId);
    public int delete(@Param("bId") long bId);
    public List<Tag> findTagsByBId( @Param("bId") long bId);
    public List<Blog> listPublishedByTId(@Param("pageResult")PageResult pageResult, @Param("tId") long tId);
    public int countPublishedByTId(@Param("tId") long tId);
}
