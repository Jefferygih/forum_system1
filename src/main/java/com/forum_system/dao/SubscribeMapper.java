package com.forum_system.dao;

import com.forum_system.entity.Subscribe;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeMapper {
   @Insert("INSERT INTO t_subscribe (theme_id, user_id) values (#{themeId}, #{userId})")
    @Options(useGeneratedKeys = true)
    int insert(Subscribe subscribe);

   @Delete("DELETE FROM t_subscribe WHERE id = #{id}")
    int delete(@Param("id") Integer id);

}
