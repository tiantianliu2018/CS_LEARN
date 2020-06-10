package com.learn.service;

import com.learn.dao.DeptDAO;

/**
 * @author Kelly
 * @create 2020-06-03 22:18
 */
public class DeptServiceImpl implements DeptService {
    // 需要 DAO 组件
    private DeptDAO deptDAO;

    public void setDeptDAO(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

    @Override
    public void save(String name) {
        System.out.println("处理 save 业务逻辑");
        deptDAO.save(name);
    }

    @Override
    public void update(String name) {
        System.out.println("处理 update 业务逻辑");
        deptDAO.update(name);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("处理 delete 业务逻辑");
        deptDAO.delete(id);
    }

    @Override
    public String find(String name) {
        System.out.println("处理 find 业务逻辑");
        deptDAO.find(name);
        return null;
    }
}
