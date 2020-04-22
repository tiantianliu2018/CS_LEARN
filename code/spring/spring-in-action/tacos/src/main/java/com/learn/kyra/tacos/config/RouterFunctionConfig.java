package com.learn.kyra.tacos.config;

import com.learn.kyra.tacos.domain.entity.Taco;
import com.learn.kyra.tacos.webflux.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

/**
 * @author liutiantian
 * @create 2020-04-12 11:54
 */
@Configuration
public class RouterFunctionConfig {
    @Autowired
    private TacoRepository tacoRepository;

    @Bean
    public RouterFunction<?> helloRouterFunction(){
        return route(GET("/hello"),
                request -> ok().body(just("Hello World"), String.class))
                .andRoute(GET("/bye"),
                        request -> ok().body(just("Bye bye!"), String.class));
    }

    @Bean
    public RouterFunction<?> routerFunction(){
        return route(GET("/design/taco"),this::recent)
                .andRoute(POST("/design"), this::postTaco);
    }

    public Mono<ServerResponse> recent(ServerRequest request){
        return ServerResponse.ok().body(tacoRepository.findAll().take(12), Taco.class);
    }
    public Mono<ServerResponse> postTaco(ServerRequest request){
        Mono<Taco> taco = request.bodyToMono(Taco.class);
        Mono<Taco> savedTaco = tacoRepository.save(taco);
        return ServerResponse
                .created(URI.create( "http://localhost:8080/design/taco/" + savedTaco.getId()))
                .body(savedTaco, Taco.class);
    }

}
