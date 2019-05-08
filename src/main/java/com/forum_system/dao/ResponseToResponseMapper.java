package com.forum_system.dao;

import com.forum_system.entity.Response;
import com.forum_system.entity.ResponseToResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResponseToResponseMapper {
    @Insert("INSERT INTO t_responsetoresponse (themeId,responseToresponseId, responseId, userId, photo,content, recoveryTime) VALUES (#{themeId},#{responseToresponseId},#{responseId}, #{userId}, #{photo}, #{content}, #{recoveryTime})")
    @Options(useGeneratedKeys = true)
    int insert(ResponseToResponse responseToResponse);

    @Select("SELECT * FROM t_responsetoresponse WHERE themeId = #{themeId}")
    List<ResponseToResponse> findByThemeId(@Param("themeId") Integer themeId);

    @Select("SELECT * FROM t_responsetoresponse WHERE responseId = #{responseId}")
    Response findById(@Param("responseId") Integer responseId);

    @Update("UPDATE t_responsetoresponse SET content = #{content}, recovery_time = #{recoveryTime} WHERE id = #{id}")
    int update(ResponseToResponse responseToResponse);

    @Select("SELECT count(id) FROM t_responsetoresponse WHERE themeId = #{themeId}")
    int findNumber(@Param("themeId") Integer themeId);

    @Delete("DELETE FROM t_responsetoresponse WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM t_responsetoresponse WHERE content LIKE '%${content}%' LIMIT #{offset}, #{size}")
    List<ResponseToResponse> findByContent(@Param("content") String content, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM t_responsetoresponse")
    List<ResponseToResponse> findAllMap();

    @Select("SELECT * FROM t_responsetoresponse WHERE id = #{id}")
    ResponseToResponse findByIdAdmin(@Param("id") Integer id);

    @Select("SELECT COUNT(id) FROM t_responsetoresponse WHERE content LIKE '%${content}%'")
    int countByContent(@Param("content") String content);

    @Select("SELECT COUNT(id) FROM t_responsetoresponse")
    int countAll();

    @Select("SELECT * FROM t_responsetoresponse LIMIT #{offset}, #{size}")
    List<ResponseToResponse> findAllAdmin(@Param("offset") int offset, @Param("size") int size);

    @Update("UPDATE t_responsetoresponse SET goods = #{goods} WHERE id = #{id}")
    void updateLike(@Param("id") Integer id, @Param("goods") int goods);

    @Select("SELECT goods FROM t_responsetoresponse WHERE id = #{id}")
    int findGoods(@Param("id") Integer id);
}
