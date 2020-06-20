package com.zuijianren.blog.service;

import com.zuijianren.blog.mapper.TagMapper;
import com.zuijianren.blog.model.PageResult;
import com.zuijianren.blog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public String saveTag(Tag tag) {
        if(tagMapper.findByName(tag.getName()) != null){
            //已存在该分类
            return "已存在该标签，添加失败";
        }
        int save = tagMapper.save(tag);
        if(save != 0){
            return "添加成功";
        }else{
            return "id重复，添加失败";
        }
    }

    @Override
    public Tag getTag(long id) {
        return tagMapper.findById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> listTagTop() {
        return tagMapper.listTop();
    }

    @Override
    public List<Tag> listTagAll() {
        return tagMapper.listAll();
    }

    @Override
    public PageResult listTag(PageResult<Tag> pageResult) {
        //根据name排序
        pageResult.setSort("name");
        int page = pageResult.getPage();
        int size = pageResult.getSize();
        String sort = pageResult.getSort();
        pageResult.setList(tagMapper.list((page-1)*size, size, sort));

        int count = tagMapper.count();
        int totalPages;
        if(count%size == 0){
            totalPages = count/size;
        }else{
            totalPages = count/size+1;
        }
        pageResult.setTotalPages(totalPages);

        //判断是否首页或者末页
        pageResult.setTotalElements(count);
        if(page == 1){
            pageResult.setFirst(true);
        }else{
            pageResult.setFirst(false);
        }
        if(page == totalPages){
            pageResult.setLast(true);
        }else{
            pageResult.setLast(false);
        }
        return pageResult;
    }

    @Override
    public String updateTag(long id, Tag tag) {
        if(tagMapper.findByName(tag.getName()) != null){
            //已存在该分类
            return "已存在该分类，更新失败";
        }
        int i = tagMapper.update(tag);
        if(i != 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }

    @Override
    public int deleteTag(long id) {
        return tagMapper.delete(id);
    }
}
