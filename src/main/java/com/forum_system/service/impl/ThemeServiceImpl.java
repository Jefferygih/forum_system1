package com.forum_system.service.impl;

import com.forum_system.dao.ThemeMapper;
import com.forum_system.entity.Page;
import com.forum_system.entity.Theme;
import com.forum_system.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThemeServiceImpl implements ThemeService {
    private final ThemeMapper themeMapper;

    @Autowired
    public ThemeServiceImpl(ThemeMapper themeMapper) {
        this.themeMapper = themeMapper;
    }

    @Override
    public int insert(Theme theme) {
        return themeMapper.insert(theme);
    }

    @Override
    public Page<Theme> findByTitle(String title, int pageSize, int page) {
        int total = themeMapper.countByTitle(title);
        List<Theme> content = themeMapper.findByTitle(title, pageSize * (page - 1), pageSize);
        Page<Theme> themePage = new Page<>();
        themePage.setContent(content);
        themePage.setCurrentPage(page);
        themePage.setTotal(total);
        themePage.setPageSize(pageSize);
        return themePage;
    }

    @Override
    public Page<Theme> findByCategoryId(Integer categoryId, int pageSize, int page) {
        int total = themeMapper.countByCategoryId(categoryId);
        List<Theme> content = themeMapper.findByCategoryId(categoryId, pageSize * (page - 1), pageSize);
        Page<Theme> themePage = new Page<>();
        themePage.setContent(content);
        themePage.setCurrentPage(page);
        themePage.setTotal(total);
        themePage.setPageSize(pageSize);
        return themePage;
    }

    @Override
    public List<Theme> findByCreatorId(Integer creatorId) {
        List<Theme> content = themeMapper.findByCreatorId(creatorId);
        return content;
    }

    @Override
    public void update(Theme theme) {
        themeMapper.update(theme);
    }

    @Override
    public void updateResNumber(String resNumber, Integer id) {
        themeMapper.updateResNumber(resNumber, id);
    }

    @Override
    public Page<Theme> findAll(int pageSize, int page) {
        int total = themeMapper.countAll();
        List<Theme> content = themeMapper.findAll(pageSize * (page - 1), pageSize);
        Page<Theme> themePage = new Page<>();
        themePage.setContent(content);
        themePage.setCurrentPage(page);
        themePage.setTotal(total);
        themePage.setPageSize(pageSize);
        return themePage;
    }

    @Override
    public Theme findById(Integer id) {
        return themeMapper.findById(id);
    }

    @Override
    public Theme findIdByNameAndContent(String title, String content) {
        return themeMapper.findIdByNameAndContent(title, content);
    }

    @Override
    public Map<Integer, String> getIdNameMap() {
        Map<Integer, String> idNameMap = new HashMap<>();
        for (Theme theme : themeMapper.findAllMap()) {
            idNameMap.put(theme.getId(), theme.getTitle());
        }
        return idNameMap;
    }

    @Override
    public int resNumberfindByCreatorId(Integer creatorId) {
        return themeMapper.resNumberfindByCreatorId(creatorId);
    }

    @Override
    public Integer findGoods(Integer id) {
        return themeMapper.findGoods(id);
    }

    @Override
    public void updateGoods(Integer id, int goods) {
        themeMapper.updateLike(id, goods);
    }

    @Override
    public Map<Integer, String> getThemeIdPhotoMap() {
        Map<Integer, String> themIdNameMap = new HashMap<>();
        for (Theme theme : themeMapper.findAllMap()) {
            themIdNameMap.put(theme.getId(), theme.getPhoto());
        }
        return themIdNameMap;
    }

    @Override
    public Page<Theme> findAnnounment(int pageSize, int page) {
        int total = themeMapper.countByAnnounment();
        List<Theme> content = themeMapper.findAnnounment(pageSize * (page - 1), pageSize);
        Page<Theme> themePage = new Page<>();
        themePage.setContent(content);
        themePage.setCurrentPage(page);
        themePage.setTotal(total);
        themePage.setPageSize(pageSize);
        return themePage;
    }


    @Override
    public List<Theme> findFirstAnnouncement() {
        return themeMapper.findFirstAnnouncement();
    }

    @Override
    public Map<Integer, Integer> getThemeIdGoodsMap() {
        Map<Integer, Integer> themeIdGoodsMap = new HashMap<>();
        for (Theme theme : themeMapper.findAllMap()) {
            themeIdGoodsMap.put(theme.getId(), theme.getGoods());
        }
        return themeIdGoodsMap;
    }

    @Override
    public void delete(Integer id) {
        themeMapper.delete(id);
    }


}
