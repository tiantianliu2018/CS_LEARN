package com.learn.service;

import com.learn.dao.UserDAO;
import com.learn.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * @author Kelly
 * @create 2020-06-20 11:08
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
