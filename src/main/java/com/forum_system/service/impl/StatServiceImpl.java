package com.forum_system.service.impl;

import com.forum_system.dao.*;
import com.forum_system.service.StatService;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {
    private final ThemeMapper themeMapper;
    private final ResponseMapper responseMapper;
    private final ResponseToResponseMapper responseToResponseMapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public StatServiceImpl(ThemeMapper themeMapper, ResponseMapper responseMapper, ResponseToResponseMapper responseToResponseMapper, UserMapper userMapper, CategoryMapper categoryMapper) {
        this.themeMapper = themeMapper;
        this.responseMapper = responseMapper;
        this.responseToResponseMapper = responseToResponseMapper;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public int getThemesCount() {
        return themeMapper.countAll();
    }

    @Override
    public int getResponsesCount() {
        return responseMapper.countAll();
    }

    @Override
    public int getResponseToResponseCount() {
        return responseToResponseMapper.countAll();
    }

    @Override
    public int getUsersCount() {
        return userMapper.getUserCount();
    }

    @Override
    public int getCategoryCount() {
        return categoryMapper.getCategoryCount();
    }
}
