package com.forum_system.service;

import com.forum_system.entity.Page;
import com.forum_system.entity.Response;
import com.forum_system.entity.ResponseToResponse;

import java.util.List;
import java.util.Map;

public interface ResponseToResponseService {
    int insert(ResponseToResponse responseToResponse);

    Response findById(Integer responseId);

    List<ResponseToResponse> findByThemeId(Integer themeId);

    int update(ResponseToResponse responseToResponse);

    int delete(Integer id);

    Integer findGoods(Integer id);

    void updateGoods(Integer id, int goods);

    int findNumber(Integer themeId);

    Map<Integer, Integer> getResponseToResIdGoodsMap();

    ResponseToResponse findByIdAdmin(Integer id);

    Map<Integer, String> getResponseToResIdPhotoMap();

    Page<ResponseToResponse> findByContent(String content, int pageSize, int page);

    Page<ResponseToResponse> findAllAdmin(int pageSize, int page);

    Map<Integer, Integer> getUserIdResponseToresponseIdMap();
}
