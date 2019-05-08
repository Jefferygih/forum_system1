package com.forum_system.dao;

import com.forum_system.entity.Theme;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeMapper {
    @Insert("INSERT INTO t_themes(categoryId, creatorId, title, content, publishTime, photo) VALUES (#{categoryId}, #{creatorId}, #{title}, #{content}, #{publishTime}, #{photo})")
    @Options(useGeneratedKeys = true)
    int insert(Theme theme);

    @Select("SELECT * FROM t_themes WHERE title = #{title} AND content = #{content}")
    Theme findIdByNameAndContent(@Param("title") String title, @Param("content") String content);

    @Select("SELECT * FROM t_themes WHERE title LIKE '%${title}%' LIMIT #{offset}, #{size}")
    List<Theme> findByTitle(@Param("title") String title, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM t_themes WHERE categoryId = #{categoryId} LIMIT #{offset}, #{size}")
    List<Theme> findByCategoryId(@Param("categoryId") Integer categoryId, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM t_themes WHERE creatorId = #{creatorId} ")
    List<Theme> findByCreatorId(@Param("creatorId") Integer creatorId);

    @Select("SELECT sum(resNumber) FROM t_themes WHERE creatorId = #{creatorId} ")
    int resNumberfindByCreatorId(@Param("creatorId") Integer creatorId);

    @Update("UPDATE t_themes SET goods = #{goods} WHERE id = #{id}")
    void updateLike(@Param("id") Integer id, @Param("goods") int goods);

    @Select("SELECT goods FROM t_themes WHERE id = #{id}")
    int findGoods(@Param("id") Integer id);




    @Select("SELECT * FROM t_themes WHERE categoryId = 1 ORDER BY id ASC LIMIT 3")
    List<Theme> findFirstAnnouncement();

    @Update("UPDATE t_themes SET categoryId = #{categoryId}, title = #{title}, content = #{content}, publishTime = #{publishTime},photo = #{photo} WHERE id = #{id}")
    int update(Theme theme);

    @Update("UPDATE t_themes SET resNumber = #{resNumber} WHERE id = #{id}")
    int updateResNumber(@Param("resNumber") String resNumber, @Param("id") Integer id);

    @Select("SELECT * FROM t_themes LIMIT #{offset}, #{size}")
    List<Theme> findAll(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM t_themes ")
    List<Theme> findAllMap();

    @Select("SELECT * FROM t_themes WHERE categoryId = '1'  LIMIT #{offset}, #{size}")
    List<Theme> findAnnounment(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(id) FROM t_themes WHERE categoryId ='1' ")
    int countByAnnounment();

    @Select("SELECT * FROM t_themes WHERE id = #{id}")
    Theme findById(@Param("id") Integer id);

    @Select("SELECT COUNT(id) FROM t_themes WHERE categoryId = #{categoryId}")
    int countByCategoryId(@Param("categoryId") Integer categoryId);

    @Select("SELECT COUNT(id) FROM t_themes")
    int countAll();

    @Select("SELECT COUNT(title) FROM t_themes WHERE title LIKE '%${title}%'")
    int countByTitle(@Param("title") String title);

    @Delete("DELETE FROM t_themes WHERE id = #{id}")
    int delete(@Param("id") Integer id);
}
