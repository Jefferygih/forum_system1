package com.forum_system.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingMapper {
    @Select("SELECT value FROM t_setting WHERE `key` = #{key}")
    String get(@Param("key") String key);

    @Delete("DELETE FROM t_setting WHERE `key` = #{key}")
    int delete(@Param("key") String key);

    @Insert("INSERT INTO t_settings VALUES (#{key}, #{value})")
    int put(@Param("key") String key, @Param("value") String value);

    @Update("UPDATE t_setting SET value = #{value} WHERE `key` = #{key}")
    int set(@Param("key") String key, @Param("value") String value);

}
