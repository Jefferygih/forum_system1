package com.forum_system.dao;

import com.forum_system.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("SELECT * FROM t_users WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM t_users")
    List<User> findAllMap();

    @Select("SELECT * FROM t_users LIMIT #{offset}, #{size}")
    List<User> findAll(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT * FROM t_users WHERE type = 'user' LIMIT #{offset}, #{size}")
    List<User> findAllUser(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM t_users WHERE  type = 'user'")
    int countAllUser();

    @Select("SELECT * FROM t_users WHERE type = 'user' AND username LIKE '%${keyword}%' LIMIT #{offset}, #{size}")
    List<User> findByNameLikeAdmin(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM t_users WHERE type = 'user' AND username LIKE '%${keyword}%'")
    int countByNameLikeAdmin(@Param("keyword") String keyword);


    @Select("SELECT COUNT(*) FROM t_users WHERE username LIKE '%${keyword}%'")
    int countByNameLike(@Param("keyword") String keyword);

    @Update("UPDATE t_users SET username = #{username}, password = #{password}, email = #{email}, status= #{status},photo = #{photo} WHERE id = #{id}")
    int update(User user);

    @Update("UPDATE t_users SET email = #{email}, activateCode = #{activateCode} WHERE id = #{id}")
    int updateEmail(@Param("id") Integer id, @Param("email") String email, @Param("activateCode") String activateCode);

    @Select("SELECT * FROM t_users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO t_users (username, password, email, activateCode, photo) VALUES (#{username}, #{password}, #{email}, #{activateCode}, #{photo})")
    @Options(useGeneratedKeys = true)
    int insert(User user);

    @Update("UPDATE t_users SET cookie = #{cookie} WHERE id = #{id}")
    int updateCookie(@Param("id") Integer id, @Param("cookie") String cookie);

    @Update("UPDATE t_users SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);

    @Update("UPDATE t_users SET activated = TRUE, activateCode = NULL WHERE activateCode = #{activateCode}")
    int activate(@Param("activateCode") String activateCode);

    @Update("UPDATE t_users SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("status") String status, @Param("id") Integer id);

    @Update("UPDATE t_users SET two_factor = #{twoFactor} WHERE id = #{id}")
    int updateTwoFactor(@Param("id") Integer id, @Param("twoFactor") String twoFactor);

    @Select("SELECT * FROM t_users WHERE username LIKE '%${keyword}%' LIMIT #{offset}, #{size}")
    List<User> findByNameLike(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(id) FROM t_users")
    int getUserCount();

    @Delete("DELETE FROM t_users WHERE id = #{id}")
    int delete(@Param("id") Integer id);

}

