package com.learn.kyra.tacos.domain;

import com.learn.kyra.tacos.domain.entity.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author liutiantian
 * @create 2020-04-09 16:41
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
