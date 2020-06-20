package com.zuijianren.blog.service;

import com.zuijianren.blog.model.BlogRequest;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.pojo.Tag;

import java.util.List;

public interface BlogTagService {
    public String getTagsIdStringByBId(Long id);
    //获取与指定blog关联的Tag
    public List<Tag> getTagsByBId(Long id);
    //获取与指定 tag 关联的 blog
    public PageResult<Blog> listBlogByTId(PageResult pageResult, Long id);
}
