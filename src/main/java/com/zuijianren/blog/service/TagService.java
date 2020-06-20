package com.zuijianren.blog.service;

import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Tag;

import java.util.List;

public interface TagService {
    public String saveTag(Tag tag);
    public Tag getTag(long id);
    public List<Tag> getAllTags();
    public List<Tag> listTagTop();
    public List<Tag> listTagAll();
    public PageResult listTag(PageResult<Tag> pageResult);
    public String updateTag(long id, Tag tag);
    public int deleteTag(long id);


}
