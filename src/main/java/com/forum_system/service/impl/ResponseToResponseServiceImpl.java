package com.forum_system.service.impl;

import com.forum_system.dao.ResponseToResponseMapper;
import com.forum_system.entity.Page;
import com.forum_system.entity.Response;
import com.forum_system.entity.ResponseToResponse;
import com.forum_system.service.ResponseToResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResponseToResponseServiceImpl implements ResponseToResponseService {
    private final ResponseToResponseMapper responseToResponseMapper;
    @Autowired
    public ResponseToResponseServiceImpl(ResponseToResponseMapper responseToResponseMapper) {
        this.responseToResponseMapper = responseToResponseMapper;
    }

    @Override
    @Transactional
    public int insert(ResponseToResponse responseToResponse) {
        return responseToResponseMapper.insert(responseToResponse);
    }


    @Override
    public Integer findGoods(Integer id) {
        return responseToResponseMapper.findGoods(id);
    }

    @Override
    public void updateGoods(Integer id, int goods) {
        responseToResponseMapper.updateLike(id, goods);
    }

    @Override
    public Response findById(Integer responseId) {
        return responseToResponseMapper.findById(responseId);
    }

    @Override
    public List<ResponseToResponse> findByThemeId(Integer themeId) {
        return responseToResponseMapper.findByThemeId(themeId);
    }

    @Override
    @Transactional
    public int update(ResponseToResponse responseToResponse) {
        return responseToResponseMapper.update(responseToResponse);
    }

    @Override
    public int findNumber(Integer themeId) {
        return responseToResponseMapper.findNumber(themeId);
    }

    @Override
    public Map<Integer, Integer> getResponseToResIdGoodsMap() {
        Map<Integer, Integer> responseToResIdGoodsMap = new HashMap<>();
        for (ResponseToResponse responseToResponse : responseToResponseMapper.findAllMap()) {
            responseToResIdGoodsMap.put(responseToResponse.getId(), responseToResponse.getGoods());
        }
        return responseToResIdGoodsMap;
    }

    @Override
    public ResponseToResponse findByIdAdmin(Integer id) {
        return responseToResponseMapper.findByIdAdmin(id);
    }

    @Override
    public Map<Integer, String> getResponseToResIdPhotoMap() {
        Map<Integer, String> responseToResIdNameMap = new HashMap<>();
        for (ResponseToResponse responseToResponse : responseToResponseMapper.findAllMap()) {
            responseToResIdNameMap.put(responseToResponse.getId(), responseToResponse.getPhoto());
        }
        return responseToResIdNameMap;
    }

    @Override
    public Page<ResponseToResponse> findByContent(String content, int pageSize, int page) {
        int total = responseToResponseMapper.countByContent(content);
        List<ResponseToResponse> content1 = responseToResponseMapper.findByContent(content,pageSize * (page - 1), pageSize);
        Page<ResponseToResponse> responseToResponsePage = new Page<>();
        responseToResponsePage.setContent(content1);
        responseToResponsePage.setCurrentPage(page);
        responseToResponsePage.setTotal(total);
        responseToResponsePage.setPageSize(pageSize);
        return responseToResponsePage;
    }

    @Override
    public Page<ResponseToResponse> findAllAdmin(int pageSize, int page) {
        int total = responseToResponseMapper.countAll();
        List<ResponseToResponse> content = responseToResponseMapper.findAllAdmin(pageSize * (page - 1), pageSize);
        Page<ResponseToResponse> responseToResponsePage = new Page<>();
        responseToResponsePage.setContent(content);
        responseToResponsePage.setCurrentPage(page);
        responseToResponsePage.setTotal(total);
        responseToResponsePage.setPageSize(pageSize);
        return responseToResponsePage;
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return responseToResponseMapper.delete(id);
    }

    @Override
    public Map<Integer, Integer> getUserIdResponseToresponseIdMap() {
        Map<Integer, Integer> userIdResponseToresponseIdMap = new HashMap<>();
        for (ResponseToResponse responseToResponse : responseToResponseMapper.findAllMap()) {
            userIdResponseToresponseIdMap.put(responseToResponse.getResponseToresponseId(), responseToResponse.getUserId());
        }
        return userIdResponseToresponseIdMap;
    }
}
