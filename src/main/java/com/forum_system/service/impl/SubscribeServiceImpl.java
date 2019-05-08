package com.forum_system.service.impl;

import com.forum_system.dao.SubscribeMapper;
import com.forum_system.entity.Subscribe;
import com.forum_system.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeMapper subscribeMapper;
    @Autowired
    public SubscribeServiceImpl(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

    @Override
    public int insert(Subscribe subscribe) {
        return subscribeMapper.insert(subscribe);
    }

    @Override
    public void delete(Integer id) {
        subscribeMapper.delete(id);
    }
}
