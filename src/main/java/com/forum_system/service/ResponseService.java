package com.forum_system.service;

import com.forum_system.entity.Page;
import com.forum_system.entity.Response;

import java.util.List;
import java.util.Map;

public interface ResponseService {
    int insert(Response response);

    List<Response> findAll(Integer themeId);

    List<Response> findByResponderId (Integer responderId);

    void update(Response response);

    void delete(Integer id);

    Integer findGoods(Integer id);

    void updateGoods(Integer id, int goods);

    Page<Response> findAllAdmin(int pageSize, int page);

    Response findByIdAdmin(Integer id);

    Page<Response> findByContent(String content, int pageSize, int page);

    String findNumber(Integer themeId);

    Map<Integer, String> getResponseIdPhotoMap();

    Map<Integer, Integer> getResponseIdGoodsMap();

    Map<Integer, Integer> getIdResponderIdMap();

}
