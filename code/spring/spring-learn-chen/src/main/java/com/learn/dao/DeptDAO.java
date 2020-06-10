package com.learn.dao;

/**
 * @author Kelly
 * @create 2020-06-03 22:15
 */
public interface DeptDAO {
    void save(String name);

    void update(String name);

    void delete(Integer id);

    String find(String name);
}
