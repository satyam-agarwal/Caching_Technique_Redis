package com.redis.repository;

import com.redis.model.User;

import java.util.Map;

public interface UserRepository {
    Map<String, User> findall();
    User getbyId(String id);
    void save(User user);
    void update(User user);
    void delete(String id);



}
