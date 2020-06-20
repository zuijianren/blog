package com.zuijianren.blog.mapper;

import com.zuijianren.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TypeMapper {
    public int count();     //查询 type 总数
    public List<Type> list(@Param("star") int star, @Param("size") int size, @Param("sort") String sort);
    public Type findById(@Param("id")long id);
    public Type findByName(@Param("name") String name);
    public List<Type> findAll();
    public List<Type> listTop();
    public List<Type> listAll();
    public int save(@Param("type") Type type);
    public int update(@Param("type") Type type);
    public int delete(@Param("id")long id);
}
