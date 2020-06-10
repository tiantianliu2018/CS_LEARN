package com.learn.dao;

/**
 * @author Kelly
 * @create 2020-06-03 22:16
 */
public class DeptDAOImpl implements DeptDAO{
    @Override
    public void save(String name) {
        System.out.println("save DAO：" + name);
    }

    @Override
    public void update(String name) {
        System.out.println("update DAO：" + name);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("delete DAO：" + id);
    }

    @Override
    public String find(String name) {
        System.out.println("find DAO：" + name);
        return name;
    }
}
