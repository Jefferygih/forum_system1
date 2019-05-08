package com.forum_system.service.impl;

import com.forum_system.dao.ResponseMapper;
import com.forum_system.entity.Page;
import com.forum_system.entity.Response;
import com.forum_system.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResponseServiceImpl implements ResponseService {
    private final ResponseMapper responseMapper;

    @Autowired
    public ResponseServiceImpl(ResponseMapper responseMapper) {
        this.responseMapper = responseMapper;
    }

    @Override
    @Transactional
    public int insert(Response response) {
        return responseMapper.insert(response);
    }

    @Override
    public List<Response> findAll(Integer themeId) {
        return responseMapper.findAll(themeId);
    }

    @Override
    public List<Response> findByResponderId(Integer responderId) {
        return responseMapper.findByResponderId(responderId);
    }

    @Override
    @Transactional
    public void update(Response response) {
        responseMapper.update(response);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        responseMapper.delete(id);
    }

    @Override
    public Integer findGoods(Integer id) {
        return responseMapper.findGoods(id);
    }

    @Override
    public void updateGoods(Integer id, int goods) {
        responseMapper.updateLike(id, goods);
    }

    @Override
    public Page<Response> findAllAdmin(int pageSize, int page) {
        int total = responseMapper.countAll();
        List<Response> content = responseMapper.findAllAdmin(pageSize * (page - 1), pageSize);
        Page<Response> responsePage = new Page<>();
        responsePage.setContent(content);
        responsePage.setCurrentPage(page);
        responsePage.setTotal(total);
        responsePage.setPageSize(pageSize);
        return responsePage;
    }

    @Override
    public Response findByIdAdmin(Integer id) {
        return responseMapper.findById(id);
    }

    @Override
    public Page<Response> findByContent(String content, int pageSize, int page) {
        int total = responseMapper.countByContent(content);
        List<Response> content1 = responseMapper.findByContent(content, pageSize * (page - 1), pageSize);
        Page<Response> responsePage = new Page<>();
        responsePage.setContent(content1);
        responsePage.setCurrentPage(page);
        responsePage.setTotal(total);
        responsePage.setPageSize(pageSize);
        return responsePage;
    }

    @Override
    public String findNumber(Integer themeId) {
        return responseMapper.findNumber(themeId);
    }


    @Override
    public Map<Integer, String> getResponseIdPhotoMap() {
        Map<Integer, String> responseIdNameMap = new HashMap<>();
        for (Response response : responseMapper.findAllMap()) {
            responseIdNameMap.put(response.getId(), response.getPhoto());
        }
        return responseIdNameMap;
    }

    @Override
    public Map<Integer, Integer> getResponseIdGoodsMap() {
        Map<Integer, Integer> responseIdGoodsMap = new HashMap<>();
        for (Response response : responseMapper.findAllMap()) {
            responseIdGoodsMap.put(response.getId(), response.getGoods());
        }
        return responseIdGoodsMap;
    }

    @Override
    public Map<Integer, Integer> getIdResponderIdMap() {
        Map<Integer, Integer> idResponderIdMap = new HashMap<>();
        for (Response response : responseMapper.findAllMap()) {
            idResponderIdMap.put(response.getId(), response.getResponderId());
        }
        return idResponderIdMap;
    }
}

