package com.learn.kyra.tacos.webflux.controller;

import com.learn.kyra.tacos.domain.entity.Taco;
import com.learn.kyra.tacos.webflux.repository.TacoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author liutiantian
 * @create 2020-04-12 11:31
 */
@RestController
@RequestMapping("/design")
public class DesignTacoController {
    private TacoRepository tacoRepository;

    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") long id){
        return tacoRepository.findById(id);
    }

    @GetMapping("/recent")
    public Flux<Taco> recentTacos(){
        return tacoRepository.findAll().take(10);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Mono<Taco> tacoMono){
        return tacoRepository.saveAll(tacoMono).next();
    }

}
