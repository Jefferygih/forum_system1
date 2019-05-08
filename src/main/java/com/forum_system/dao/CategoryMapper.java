package com.forum_system.dao;

import com.forum_system.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    @Select("SELECT * FROM t_category")
    List<Category> findAll();

    @Select("SELECT * FROM t_category WHERE id != '1'")
    List<Category> findAll1();

    @Select("SELECT * FROM t_category ")
    List<Category> findAllMap();

    @Select("SELECT * FROM t_category WHERE id = #{id}")
    Category findById(@Param("id") Integer id);

    @Insert("INSERT INTO t_category(title, content, color) VALUES (#{title}, #{content}, #{color})")
    @Options(useGeneratedKeys = true)
    int insert(Category category);

    @Update("UPDATE t_category SET title = #{title}, content = #{content}, color = #{color}  WHERE id = #{id}")
    int update(Category category);

    @Delete("DELETE FROM t_category WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT COUNT(id) FROM t_category")
    int getCategoryCount();


}
