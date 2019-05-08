package com.forum_system.dao;

import com.forum_system.entity.Letter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterMapper {
    @Insert("INSERT INTO t_letter (recipientId, senderId, sendContent,adminContent, sendTime) VALUES (#{recipientId}, #{senderId}, #{sendContent},#{adminContent}, #{sendTime})")
    @Options(useGeneratedKeys = true)
    int insert(Letter letter);

    @Select("SELECT * FROM t_letter WHERE recipientId = #{recipientId}")
    List<Letter> findAllReceive(@Param("recipientId") Integer recipientId);

    @Select("SELECT * FROM t_letter WHERE senderId = #{senderId}")
    List<Letter> findAllSend(@Param("senderId") Integer senderId);

    @Select("SELECT * FROM t_letter LIMIT #{offset}, #{size}")
    List<Letter> findAllLetter(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(id) FROM t_letter")
    int countAll();

    @Select("SELECT * FROM t_letter WHERE senderId = #{senderId} ")
    List<Letter> findBySenderId(@Param("senderId") Integer senderId);

    @Delete("DELETE FROM t_letter WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM t_letter WHERE id = #{id}")
    Letter findById(@Param("id") Integer id);

    @Update("UPDATE t_letter SET recipientId = #{recipientId}, senderId = #{senderId}, sendContent=#{sendContent},adminContent = #{adminContent},sendTime = #{sendTime} WHERE id = #{id}")
    int update(Letter letter);
}
