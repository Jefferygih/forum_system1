package com.forum_system.service;

import com.forum_system.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> findAll();

    List<Category> findAll1();

    Category findById(Integer id);

    void update(Category category);

    int insert(Category category);

    void delete(Integer id);

    Map<Integer, String> getIdColorMap();

    Map<Integer, String> getIdContentMap();

    Map<Integer, String> getIdNameMap();
}
