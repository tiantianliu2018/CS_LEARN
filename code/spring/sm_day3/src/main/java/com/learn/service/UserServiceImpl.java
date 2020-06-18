package com.learn.service;

import com.learn.dao.UserDAO;
import com.learn.entity.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.UUID;

/**
 * @author Kelly
 * @create 2020-06-12 07:59
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    // 生成事务管理器
    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void save(User user) {
        // 创建事务配置对象
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 获取事务状态
        TransactionStatus status = transactionManager.getTransaction(definition);
        // 控制事务  处理业务
        try {
            user.setId(UUID.randomUUID().toString());
            this.userDAO.save(user);
//            int i = 1/0;
            transactionManager.commit(status);
        } catch (Exception e){
            transactionManager.rollback(status);
        }

    }
}
