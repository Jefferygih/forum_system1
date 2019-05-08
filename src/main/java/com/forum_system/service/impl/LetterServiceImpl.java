package com.forum_system.service.impl;

import com.forum_system.dao.LetterMapper;
import com.forum_system.entity.Letter;
import com.forum_system.entity.Page;
import com.forum_system.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {
    private final LetterMapper letterMapper;

    @Autowired
    public LetterServiceImpl(LetterMapper letterMapper) {
        this.letterMapper = letterMapper;
    }

    @Override
    public List<Letter> findAllReceive(Integer recipientId) {
        return letterMapper.findAllReceive(recipientId);
    }

    @Override
    @Transactional
    public int insert(Letter letter) {
        return letterMapper.insert(letter);
    }

    @Override
    @Transactional
    public List<Letter> findAllSend(Integer senderId) {
        return letterMapper.findAllSend(senderId);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        letterMapper.delete(id);
    }

    @Override
    public Letter findById(Integer id) {
        return letterMapper.findById(id);
    }

    @Override
    public void update(Letter letter) {
        letterMapper.update(letter);

    }

    @Override
    public Page<Letter> findAllLetter(int pageSize, int page) {
        int total = letterMapper.countAll();
        List<Letter> content = letterMapper.findAllLetter(pageSize * (page - 1), pageSize);
        Page<Letter> responsePage = new Page<>();
        responsePage.setContent(content);
        responsePage.setCurrentPage(page);
        responsePage.setTotal(total);
        responsePage.setPageSize(pageSize);
        return responsePage;
    }

    @Override
    public List<Letter> findBySenderId(Integer senderId) {
        return letterMapper.findBySenderId(senderId);
    }
}
