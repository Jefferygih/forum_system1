package com.forum_system.service.impl;

import com.forum_system.dao.UserMapper;
import com.forum_system.entity.Page;
import com.forum_system.entity.User;
import com.forum_system.service.UserService;
import com.forum_system.util.MD5Util;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final GoogleAuthenticator ga = new GoogleAuthenticator();

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User login(User userToLogin) {
        User user = userMapper.findByUsername(userToLogin.getUsername());
        if (user != null) {
            if (MD5Util.verifySaltedString(user.getPassword(), userToLogin.getPassword())) {
                if (user.getTwoFactor() == null) {
                    String cookie = user.getUsername() + UUID.randomUUID().toString();
                    userMapper.updateCookie(user.getId(), cookie);
                    user.setCookie(cookie);
                } else {
                    user.setTwoFactor("");
                }
                user.setPassword(null);
                return user;
            }
        }
        return null;
    }

    @Override
    public User twoFactorAuth(User userToLogin, Integer twoFACode) {
        User user = userMapper.findById(userToLogin.getId());
        if (ga.authorize(user.getTwoFactor(), twoFACode)) {
            String cookie = user.getUsername() + UUID.randomUUID().toString();
            userMapper.updateCookie(user.getId(), cookie);
            user.setCookie(cookie);
            user.setPassword(null);
            user.setTwoFactor("");
            return user;
        }
        return null;
    }

    @Override
    public int register(User user) {
        user.setPassword(MD5Util.saltEncrypt(user.getPassword()));
        user.setActivateCode(MD5Util.encrypt(user.getUsername()) + MD5Util.encrypt(user.getEmail()));
        return userMapper.insert(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return false;
    }

    @Override
    @Transactional
    public boolean activate(String activateCode) {
        if (!activateCode.isEmpty()) {
            return userMapper.activate(activateCode) > 0;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void updateEmail(User user) {
        user.setActivateCode(MD5Util.encrypt(user.getUsername()) + MD5Util.encrypt(user.getEmail()));
        userMapper.updateEmail(user.getId(), user.getEmail(), user.getActivateCode());

    }

    @Override
    @Transactional
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return MD5Util.verifySaltedString(userMapper.findById(user.getId()).getPassword(), password);
    }

    @Override
    @Transactional
    public void updatePassword(User user) {
        user.setPassword(MD5Util.saltEncrypt(user.getPassword()));
        userMapper.updatePassword(user.getId(), user.getPassword());
    }

    @Override
    public boolean checkTwoFactor(User user, Integer twoFACode) {
        return ga.authorize(userMapper.findById(user.getId()).getTwoFactor(), twoFACode);
    }

    @Override
    public void updateTwoFactor(User user) {
        userMapper.updateTwoFactor(user.getId(), user.getTwoFactor());
    }


    @Override
    public Page<User> findAll(int pageSize, int page) {
        int total = userMapper.getUserCount();
        List<User> content = userMapper.findAll(pageSize * (page - 1), pageSize);
        Page<User> userPage = new Page<>();
        userPage.setContent(content);
        userPage.setCurrentPage(page);
        userPage.setTotal(total);
        userPage.setPageSize(pageSize);
        return userPage;
    }

    @Override
    public Page<User> findByNameLikeAdmin(String keyword, int pageSize, int page) {
        int total = userMapper.countByNameLikeAdmin(keyword);
        List<User> content = userMapper.findByNameLikeAdmin(keyword, pageSize * (page - 1), pageSize);
        Page<User> userPage = new Page<>();
        userPage.setContent(content);
        userPage.setCurrentPage(page);
        userPage.setTotal(total);
        userPage.setPageSize(pageSize);
        return userPage;
    }

    @Override
    public Page<User> findAllUser(int pageSize, int page) {
        int total = userMapper.countAllUser();
        List<User> content = userMapper.findAllUser(pageSize * (page - 1), pageSize);
        Page<User> userPage = new Page<>();
        userPage.setContent(content);
        userPage.setCurrentPage(page);
        userPage.setTotal(total);
        userPage.setPageSize(pageSize);
        return userPage;
    }

    @Override
    public Page<User> findByNameLike(String keyword, int pageSize, int page) {
        int total = userMapper.countByNameLike(keyword);
        List<User> content = userMapper.findByNameLike(keyword, pageSize * (page - 1), pageSize);
        Page<User> userPage = new Page<>();
        userPage.setContent(content);
        userPage.setCurrentPage(page);
        userPage.setTotal(total);
        userPage.setPageSize(pageSize);
        return userPage;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Map<Integer, String> getIdPhotoMap() {
        Map<Integer, String> idPhotoMap = new HashMap<>();
        for (User user : userMapper.findAllMap()) {
            idPhotoMap.put(user.getId(), user.getPhoto());
        }
        return idPhotoMap;
    }

    @Override
    public Map<Integer, String> getIdNameMap() {
        Map<Integer, String> idNameMap = new HashMap<>();
        for (User user : userMapper.findAllMap()) {
            idNameMap.put(user.getId(), user.getUsername());
        }
        return idNameMap;
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
