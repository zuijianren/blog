package com.zuijianren.blog.mapper;

import com.zuijianren.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TagMapper {
    public int count();     //查询 type 总数
    public List<Tag> list(@Param("star") int star, @Param("size") int size, @Param("sort")String sort);
    public Tag findById(@Param("id") long id);
    public Tag findByName(@Param("name") String name);
    public List<Tag> findAll();
    public List<Tag> listTop();
    public List<Tag> listAll();
    public int save(@Param("tag") Tag tag);
    public int update(@Param("tag") Tag tag);
    public int delete(@Param("id") long id);


}
