package com.bootcamp.java.credit.web;

import com.bootcamp.java.credit.domain.Credit;
import com.bootcamp.java.credit.service.CreditService;
import com.bootcamp.java.credit.web.mapper.CreditMapper;
import com.bootcamp.java.credit.web.model.CreditModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/credit")
public class CreditController {
    @Value("${spring.application.name}")
    String name;
    @Value("${server.port}")
    String port;

    @Autowired
    private CreditService clientService;
    @Autowired
    private CreditMapper clientMapper;
    @GetMapping
    public Mono<ResponseEntity<Flux<CreditModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(clientService.findAll()
                        .map(customer -> clientMapper.entityToModel(customer))));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreditModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<Credit> response = clientService.findById(id);
        return response
                .map(customer -> clientMapper.entityToModel(customer))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CreditModel>> create(@Valid @RequestBody CreditModel
                                                            request){
        log.info("create executed {}", request);
        return clientService.create(clientMapper.modelToEntity(request))
                .map(customer -> clientMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "customer", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreditModel>> updateById(@PathVariable String id, @Valid
    @RequestBody CreditModel request){
        log.info("updateById executed {}:{}", id, request);
        return clientService.update(id, clientMapper.modelToEntity(request))
                .map(customer -> clientMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "customer", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return clientService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
