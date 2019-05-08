package com.forum_system.service;

import com.forum_system.entity.Page;
import com.forum_system.entity.User;

import java.util.Map;

public interface UserService {
    User login(User userToLogin);

    User twoFactorAuth(User userToLogin, Integer twoFACode);

    int register(User user);

    boolean usernameExists(String username);

    boolean activate(String activateCode);

    void updateEmail(User user);

    void update(User user);

    boolean checkPassword(User user, String password);

    void updatePassword(User user);

    boolean checkTwoFactor(User user, Integer twoFACode);

    void updateTwoFactor(User user);

    Page<User> findAll(int pageSize, int page);

    Page<User> findByNameLikeAdmin (String keyword, int pageSize, int page);

    Page<User> findAllUser (int pageSize, int page);

    Page<User> findByNameLike(String keyword, int pageSize, int page);

    User findById(Integer id);

    Map<Integer, String> getIdPhotoMap();

    Map<Integer, String> getIdNameMap();

    void delete(Integer id);
}
