package com.forum_system.dao;

import com.forum_system.entity.Response;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseMapper {
    @Insert("INSERT INTO t_response (themeId, responderId, recoveryTime, content, photo) VALUES (#{themeId}, #{responderId}, #{recoveryTime}, #{content}, #{photo})")
    @Options(useGeneratedKeys = true)
    int insert(Response response);

    @Select("SELECT * FROM t_response WHERE themeId = #{themeId}")
    List<Response> findAll(@Param("themeId") Integer themeId);

    @Select("SELECT * FROM t_response WHERE content LIKE '%${content}%' LIMIT #{offset}, #{size}")
    List<Response> findByContent(@Param("content") String content, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM t_response WHERE responderId = #{responderId} ")
    List<Response> findByResponderId(@Param("responderId") Integer responderId);


    @Select("SELECT * FROM t_response LIMIT #{offset}, #{size}")
    List<Response> findAllAdmin(@Param("offset") int offset, @Param("size") int size);

    @Update("UPDATE t_response SET content = #{content}, responderId = #{responderId}, photo=#{photo},themeId = #{themeId},goods = #{goods},recoveryTime = #{recoveryTime} WHERE id = #{id}")
    int update(Response response);

    @Select("SELECT COUNT(id) FROM t_response WHERE content LIKE '%${content}%'")
    int countByContent(@Param("content") String content);

    @Update("UPDATE t_response SET goods = #{goods} WHERE id = #{id}")
    void updateLike(@Param("id") Integer id, @Param("goods") int goods);

    @Select("SELECT goods FROM t_response WHERE id = #{id}")
    int findGoods(@Param("id") Integer id);

    @Select("SELECT count(id) FROM t_response WHERE themeId = #{themeId}")
    String findNumber(@Param("themeId") Integer themeId);

    @Select("SELECT * FROM t_response WHERE id = #{id}")
    Response findById(@Param("id") Integer id);

    @Delete("DELETE FROM t_response WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM t_response")
    List<Response> findAllMap();

    @Select("SELECT COUNT(id) FROM t_response")
    int countAll();
}
