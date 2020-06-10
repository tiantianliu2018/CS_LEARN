package com.learn.service;

/**
 * @author Kelly
 * @create 2020-06-03 22:18
 */
public interface DeptService {
    void save(String name);

    void update(String name);

    void delete(Integer id);

    String find(String name);
}
