package com.forum_system.service;

import com.forum_system.entity.Letter;
import com.forum_system.entity.Page;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LetterService {
    List<Letter> findAllReceive(Integer recipientId);

    int insert(Letter letter);

    List<Letter> findAllSend(Integer senderId);

    void delete(Integer id);

    Letter findById(Integer id);

    void update(Letter letter);

    Page<Letter> findAllLetter(int pageSize, int page);

    List<Letter> findBySenderId(Integer senderId);

}

