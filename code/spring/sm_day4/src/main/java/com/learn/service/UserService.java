package com.learn.service;

import com.learn.entity.User;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-14 08:30
 */
public interface UserService {
    List<User> findALl();
    void save(User user);
}
