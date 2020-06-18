package com.learn.service;

import com.learn.entity.User;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-12 07:58
 */
public interface UserService {
    List<User> findAll();
    void save(User user);
}
