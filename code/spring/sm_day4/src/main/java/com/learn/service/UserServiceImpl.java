package com.learn.service;

import com.learn.dao.UserDAO;
import com.learn.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * @author Kelly
 * @create 2020-06-14 08:30
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findALl() {
        return userDAO.findAll();
    }

    @Override
    public void save(User user) {
        // 处理业务逻辑
        user.setId(UUID.randomUUID().toString());
        userDAO.save(user);
    }
}
