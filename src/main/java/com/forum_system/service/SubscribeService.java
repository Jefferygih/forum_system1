package com.forum_system.service;

import com.forum_system.entity.Subscribe;

public interface SubscribeService {
    int insert(Subscribe subscribe);

    void delete(Integer id);
}
