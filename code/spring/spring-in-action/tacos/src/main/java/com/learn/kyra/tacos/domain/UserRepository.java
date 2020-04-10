package com.learn.kyra.tacos.domain;

import com.learn.kyra.tacos.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liutiantian
 * @create 2020-04-09 16:44
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
