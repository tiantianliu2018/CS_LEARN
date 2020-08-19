package com.learn.service;

import com.learn.entity.User;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-20 11:07
 */
public interface UserService {
    void save(User user);
    List<User> findAll();
}
