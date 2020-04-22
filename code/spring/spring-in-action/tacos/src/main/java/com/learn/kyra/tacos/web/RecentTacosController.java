package com.learn.kyra.tacos.web;

import com.learn.kyra.tacos.domain.TacoRepository;
import com.learn.kyra.tacos.domain.entity.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

/**
 * @author liutiantian
 * @create 2020-04-10 14:20
 */
// 采用 Spring Data REST 的基本路径进行其请求映射
@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0, 12,
                Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    public Flux<Taco> recentTaco(){
        return null;
    }
}
