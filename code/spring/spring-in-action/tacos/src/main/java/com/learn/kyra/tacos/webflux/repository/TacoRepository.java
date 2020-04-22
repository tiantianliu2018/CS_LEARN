package com.learn.kyra.tacos.webflux.repository;

import com.learn.kyra.tacos.domain.entity.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author liutiantian
 * @create 2020-04-12 11:29
 */
public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {
}
