package com.zuijianren.blog.service;

import com.zuijianren.blog.mapper.TypeMapper;
import com.zuijianren.blog.pojo.Type;
import com.zuijianren.blog.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public String saveType(Type type) {
        if(typeMapper.findByName(type.getName()) != null){
            //已存在该分类
            return "已存在该分类，添加失败";
        }
        int save = typeMapper.save(type);
        if(save != 0){
            return "添加成功";
        }else{
            return "id重复，添加失败";
        }
    }

    @Override
    public Type getType(long id) {
        return typeMapper.findById(id);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.findAll();
    }

    @Override
    public List<Type> listTypeTop() {
        return typeMapper.listTop();
    }

    @Override
    public List<Type> listTypeAll() {
        return typeMapper.listAll();
    }

    @Override
    public PageResult listType(PageResult<Type> pageResult) {
        //根据name排序
        pageResult.setSort("name");
        int page = pageResult.getPage();
        int size = pageResult.getSize();
        String sort = pageResult.getSort();
        pageResult.setList(typeMapper.list((page-1)*size, size, sort));

        int count = typeMapper.count();
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
    public String updateType(long id, Type type) {
        if(typeMapper.findByName(type.getName()) != null){
            //已存在该分类
            return "已存在该分类，更新失败";
        }
        int i = typeMapper.update(type);
        if(i != 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }

    @Override
    public int deleteType(long id) {
        return typeMapper.delete(id);
    }
}
