package com.forum_system.service;

import com.forum_system.entity.Page;
import com.forum_system.entity.Theme;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

public interface ThemeService {
    int insert(Theme theme);

    Page<Theme> findByTitle(String title, int pageSize, int page);

    Page<Theme> findByCategoryId(Integer categoryId, int pageSize, int page);

    List<Theme> findByCreatorId(Integer creatorId);

    void update(Theme theme);

    void updateResNumber(String resNumber, Integer id);

    Page<Theme> findAll(int pageSize, int page);

    Theme findById(Integer id);

    Theme findIdByNameAndContent(String title, String content);

    Map<Integer, String> getIdNameMap();

    int resNumberfindByCreatorId(Integer creatorId);

    Map<Integer, String> getThemeIdPhotoMap();

    Page<Theme> findAnnounment(int pageSize, int page);

    List<Theme> findFirstAnnouncement();

    Map<Integer, Integer> getThemeIdGoodsMap();

    Integer findGoods(Integer id);

    void updateGoods(Integer id, int goods);

    void delete(Integer id);


}
