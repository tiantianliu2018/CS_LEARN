package com.learn.kyra.tacos.domain;

import com.learn.kyra.tacos.domain.entity.Order;
import com.learn.kyra.tacos.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author liutiantian
 * @create 2020-04-09 16:42
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user);
}
