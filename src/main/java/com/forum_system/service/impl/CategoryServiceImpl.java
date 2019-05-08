package com.forum_system.service.impl;

import com.forum_system.dao.CategoryMapper;
import com.forum_system.entity.Category;
import com.forum_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findAll1() {
        return categoryMapper.findAll1();
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    @Transactional
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    @Transactional
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoryMapper.delete(id);

    }

    @Override
    public Map<Integer, String> getIdColorMap() {
        Map<Integer, String> categoryIdColorMap = new HashMap<>();
        for (Category category : categoryMapper.findAllMap()) {
            categoryIdColorMap.put(category.getId(), category.getColor());
        }
        return categoryIdColorMap;
    }

    @Override
    public Map<Integer, String> getIdContentMap() {
        Map<Integer, String> categoryIdContentMap = new HashMap<>();
        for (Category category : categoryMapper.findAllMap()) {
            categoryIdContentMap.put(category.getId(), category.getContent());
        }
        return categoryIdContentMap;
    }

    @Override
    public Map<Integer, String> getIdNameMap() {
        Map<Integer, String> idNameMap = new HashMap<>();
        for (Category category : categoryMapper.findAll()) {
            idNameMap.put(category.getId(), category.getTitle());
        }
        return idNameMap;
    }
}
