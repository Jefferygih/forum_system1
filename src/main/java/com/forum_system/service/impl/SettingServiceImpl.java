package com.forum_system.service.impl;

import com.forum_system.dao.SettingMapper;
import com.forum_system.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SettingServiceImpl implements SettingService {
    private final SettingMapper settingMapper;
    @Autowired
    public SettingServiceImpl(SettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Override
    public String get(String key) {
        return settingMapper.get(key);
    }

    @Override
    @Transactional
    public void delete(String key) {
        settingMapper.delete(key);
    }

    @Override
    @Transactional
    public int put(String key, String value) {
        return settingMapper.put(key,value);
    }

    @Override
    @Transactional
    public void set(String key, String value) {
       settingMapper.set(key, value);
    }
}
