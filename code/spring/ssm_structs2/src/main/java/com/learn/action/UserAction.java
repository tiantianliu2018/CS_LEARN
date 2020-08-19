package com.learn.action;

import com.learn.entity.User;
import com.learn.service.UserService;
import com.opensymphony.xwork2.Action;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-20 14:46
 */
public class UserAction {
    private UserService userService;
    private List<User> users;
    private User user;

    public String save(){
        userService.save(user);
        return Action.SUCCESS;
    }

    //    查询所有用户
    public String findAll(){
        // 收集数据 -- 调用业务 -- 处理响应
        this.users = userService.findAll();  // 用成员变量接收参数
        return Action.SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
