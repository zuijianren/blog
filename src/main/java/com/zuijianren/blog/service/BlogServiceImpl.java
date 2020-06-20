package com.zuijianren.blog.service;

import com.zuijianren.blog.exception.BlogNotFoundException;
import com.zuijianren.blog.mapper.BlogMapper;
import com.zuijianren.blog.mapper.BlogTagMapper;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.model.BlogRequest;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.util.MarkdownUtils;
import com.zuijianren.blog.util.TagIdsUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public int countPublished() {
        return blogMapper.countPublished();
    }

    @Override
    @Transactional
    public String saveBlog(Blog blog, String tagIds) {
    //将数据插入blog表和tag-blog表
        //生成id
        blog.setId();

        //插入blog表
        blogMapper.save(blog);

        //1. 对tagIds进行处理，添加记录到blog_tag表
        String[] tagId = TagIdsUtils.getTagIds(tagIds);
        for (String s : tagId) {
            Long tId = new Long(s);
            blogTagMapper.save(blog.getId(), tId);
        }

        //判断是否已经存在相同标题
        if(blogMapper.countByTitle(blog.getTitle()) > 1){
            return "添加成功，但已存在相同标题，建议更换标题";
        }
        return "添加成功";
    }

    @Transactional
    @Override
    public String deleteBlog(long id) {
        //删除blog_tag表中的记录
        blogTagMapper.delete(id);
        //删除blog表
        blogMapper.delete(id);
        return "删除成功";
    }

    @Override
    public String updateBlog(Blog blog, String tagIds) {
        //更新blog
        blogMapper.update(blog);
    //更新blog_tag表
        //1. 删除与该blog相关的blog_tag的记录
        blogTagMapper.delete(blog.getId());
        //2. 添加新的记录
        String[] tagId = TagIdsUtils.getTagIds(tagIds);
        for (String s : tagId) {
            Long tId = new Long(s);
            blogTagMapper.save(blog.getId(), tId);
        }

        //判断是否已经存在相同标题
        if(blogMapper.countByTitle(blog.getTitle()) > 1){
            return "更新成功，但已存在相同标题，建议更换标题";
        }
        return "更新成功";
    }

    @Override
    public Blog getBlogById(long id) {
        return blogMapper.findById(id);
    }

    @Override
    public Blog getAndConvertBlog(long id) {
        Blog blog = blogMapper.findById(id);
        if(blog ==null){
            throw new BlogNotFoundException("该博客已被删除");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public Map<String, List<Blog>> MapDateBlog() {
        Map<String, List<Blog>> result = new LinkedHashMap<>();
        List<String> dates = blogMapper.listDate();
        for (String date : dates) {
            List<Blog> blogs = blogMapper.listPublishedByDate(date);
            result.put(date, blogs);
        }
        return result;
    }

    @Override
    public List<Blog> listBlogRecommendTop() {
        return blogMapper.listPublishedRecommendTop();
    }

    @Override
    public PageResult listBlog(PageResult<Blog> pageResult) {

        int count = blogMapper.count();
        pageResult.setTotalElements(count);

        pageResult.fill();

        pageResult.setList(blogMapper.list(pageResult));

        return pageResult;
    }

    @Override
    public PageResult listBlogPublished(PageResult<Blog> pageResult) {
        int count = blogMapper.countPublished();
        pageResult.setTotalElements(count);

        pageResult.fill();

        //根据updateTime排序，反序，每页6条数据
        pageResult.setSort("updateTime");
        pageResult.setDesc(true);
        pageResult.setSize(6);
        pageResult.setList(blogMapper.listPublished(pageResult));

        return pageResult;
    }

    @Override
    public PageResult listBlogPublishedSearch(PageResult<Blog> pageResult, String query) {
        int count = blogMapper.countPublishedSearch(query);
        pageResult.setTotalElements(count);

        pageResult.fill();

        pageResult.setList(blogMapper.listPublishedSearch(pageResult, query));

        return pageResult;
    }

    @Override
    public PageResult<Blog> listBlogByTId(PageResult pageResult, Long tId) {
        int count = blogMapper.countPublishedByTId(tId);
        pageResult.setTotalElements(count);

        pageResult.fill();

        pageResult.setList(blogMapper.listPublishedByTId(pageResult, tId));
        return pageResult;
    }

    @Override
    public PageResult listBlogSearch(PageResult<Blog> pageResult, BlogRequest blogRequest) {
        int count = blogMapper.searchCount( blogRequest);
        pageResult.setTotalElements(count);

        pageResult.fill();

        pageResult.setList(blogMapper.listSearch(pageResult, blogRequest));

        return pageResult;
    }


}
