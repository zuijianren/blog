package com.zuijianren.blog.service;

import com.zuijianren.blog.mapper.BlogTagMapper;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Blog;
import com.zuijianren.blog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService{
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public String getTagsIdStringByBId(Long id) {
        //查找tag
        List<Tag> tagByBId = blogTagMapper.findTagsByBId(id);
        StringBuilder builder = new StringBuilder();
        int size = tagByBId.size();
        for (int i = 0; i < size-1; i++) {
            builder.append(tagByBId.get(i).getId());
            builder.append(",");
        }
        builder.append(tagByBId.get(size-1).getId());
        return builder.toString();
    }

    @Override
    public List<Tag> getTagsByBId(Long id) {
        return blogTagMapper.findTagsByBId(id);
    }

    @Override
    public PageResult<Blog> listBlogByTId(PageResult pageResult, Long id) {
        int count = blogTagMapper.countPublishedByTId(id);
        pageResult.setTotalElements(count);

        pageResult.fill();

        pageResult.setList(blogTagMapper.listPublishedByTId(pageResult, id));

        return pageResult;
    }
}
