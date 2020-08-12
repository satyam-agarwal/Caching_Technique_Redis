package com.redis.service;

import com.redis.model.User;
import com.redis.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/user")
public class Usercall {
    private UserRepository userRepository;

    public Usercall(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/find/all")
    public Map<String, User> getall(){
        return userRepository.findall();
    }
    @GetMapping("/find/{id}")
    @Cacheable(value = "USER",key = "#id")
    public User findbyId(@PathVariable("id") String id){
        return userRepository.getbyId(id);
    }
    @GetMapping("/add/{id}/{name}")
    @Cacheable(value = "USER",key = "#id")
    public User addUser(@PathVariable("id") String id, @PathVariable("name") String name){
        userRepository.save(new User(id,name,200000L));
        return userRepository.getbyId(id);
    }
    @GetMapping("/update/{id}/{name}")
    @CachePut(value = "USER",key = "#id")
    public User updateUser(@PathVariable("id") String id, @PathVariable("name") String name){
        userRepository.update(new User(id,name,10000000L));
        return userRepository.getbyId(id);
    }
    @GetMapping("/delete/{id}")
    @CacheEvict(value="USER",key = "#id")
    public Map<String, User> deleteUser(@PathVariable("id") String id){
        userRepository.delete(id);
        return userRepository.findall();
    }

}
