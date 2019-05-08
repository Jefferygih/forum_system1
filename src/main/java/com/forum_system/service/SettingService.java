package com.forum_system.service;

public interface SettingService {
    String get(String key);

    void delete(String key);

    int put(String key, String value);

    void set(String key, String value);
}
