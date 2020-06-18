package com.learn.dao;

import com.learn.entity.User;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-14 08:26
 */
public interface UserDAO {
    List<User> findAll();
    void save(User user);
}
