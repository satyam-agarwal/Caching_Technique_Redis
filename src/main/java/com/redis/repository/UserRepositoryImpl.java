package com.redis.repository;

import com.redis.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Map<String, User> findall() {
        return hashOperations.entries("USER");
    }

    @Override
    public void save(User user) {
        hashOperations.put("USER",user.getId(),user);
    }

    @Override
    public void update(User user) {
        hashOperations.put("USER",user.getId(),user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("USER",id);
    }

    @Override
    public User getbyId(String id) {
        return (User)hashOperations.get("USER",id);
    }
}
