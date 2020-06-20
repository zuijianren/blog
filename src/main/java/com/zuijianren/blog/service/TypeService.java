package com.zuijianren.blog.service;

import com.zuijianren.blog.pojo.Type;
import com.zuijianren.blog.model.PageResult;

import java.util.List;

public interface TypeService {
    public String saveType(Type type);
    public Type getType(long id);
    public List<Type> getAllTypes();
    public List<Type> listTypeTop();
    public List<Type> listTypeAll();
    public PageResult listType(PageResult<Type> pageResult);
    public String updateType(long id, Type type);
    public int deleteType(long id);
}
