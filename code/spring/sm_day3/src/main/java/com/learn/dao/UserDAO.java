package com.learn.dao;

import com.learn.entity.User;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-10 07:52
 */
public interface UserDAO {
    List<User> findAll();
}
