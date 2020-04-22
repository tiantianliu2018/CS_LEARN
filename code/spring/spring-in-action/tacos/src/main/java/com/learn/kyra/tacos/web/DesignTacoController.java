package com.learn.kyra.tacos.web;

import com.learn.kyra.tacos.domain.TacoRepository;
import com.learn.kyra.tacos.domain.entity.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;

/**
 * @author liutiantian
 * @create 2020-04-09 16:47
 */
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {
    private TacoRepository tacoRepository;

    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isPresent()){
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable).getContent();
    }
}
