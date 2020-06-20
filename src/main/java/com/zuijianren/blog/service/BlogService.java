package com.zuijianren.blog.service;

import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.model.BlogRequest;
import com.zuijianren.blog.model.PageResult;

import java.util.List;
import java.util.Map;

public interface BlogService {

    public int countPublished();

    public String saveBlog(Blog blog, String tagIds);

    public String deleteBlog(long id);     //admin下删除blog

    public String updateBlog(Blog blog, String tagIds);     //admin下更新blog


    public Blog getBlogById(long id);
    public Blog getAndConvertBlog(long id);    //获取blog，并将content中的markdown转化为blog，不更改数据库

    public Map<String, List<Blog>> MapDateBlog(); //获取（指定年月发表，blog），根据 createTime排序

    public List<Blog> listBlogRecommendTop();   //按updateTime排序的推荐的已发布的全部的博客

    public PageResult listBlog(PageResult<Blog> pageResult);    //全部博客，根据title排序
    public PageResult listBlogPublished(PageResult<Blog> pageResult);   //最新发布博客
    public PageResult listBlogPublishedSearch(PageResult<Blog> pageResult, String query);
    public PageResult listBlogByTId(PageResult<Blog> pageResult, Long tId);     //查找某类型的所有已发布的博客
    public PageResult listBlogSearch(PageResult<Blog> pageResult, BlogRequest blogRequest);     //admin下查找blog



}
