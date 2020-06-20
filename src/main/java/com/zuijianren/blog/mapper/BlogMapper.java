package com.zuijianren.blog.mapper;

import com.zuijianren.blog.model.BlogRequest;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogMapper {
    public int count();
    public int countByTitle(@Param("title") String title);          //查询是否存在重复的标题
    public int countPublished();
    public int countPublishedSearch(@Param("query") String query);          //首页的搜索查询计数
    public int countPublishedByTId(@Param("tId") long tId);
    public int searchCount(@Param("blogRequest") BlogRequest blogRequest);      //admin下的查询计数

    public List<String> listDate();     //返回所有的时间值

    public List<Blog> listPublishedRecommendTop();      //最新推荐8条博客
    public List<Blog> listPublishedByDate(@Param("date") String date);  //根据时间查询date
    public List<Blog> list(@Param("pageResult") PageResult pageResult);
    public List<Blog> listPublished(@Param("pageResult") PageResult pageResult);
    public List<Blog> listPublishedSearch(@Param("pageResult") PageResult pageResult, @Param("query")String query);
    public List<Blog> listPublishedByTId(@Param("pageResult") PageResult pageResult, @Param("tId") long tId);   //获取type对应的全部已发布的博客
    public List<Blog> listSearch(@Param("pageResult") PageResult pageResult, @Param("blogRequest") BlogRequest blogRequest);    //admin下的blog查询

    public Blog findById(@Param("id") long id);

    public int update(@Param("blog") Blog blog);
    public void updateViews(@Param("id") long id);

    public int delete(@Param("id") long id);

    public int save(@Param("blog") Blog blog);


}
