package com.bootcamp.java.credit.service;

import com.bootcamp.java.credit.domain.Credit;
import com.bootcamp.java.credit.repository.CreditRepository;
import com.bootcamp.java.credit.web.mapper.CreditMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CreditService {
    @Autowired
    private CreditRepository clientRepository;

    @Autowired
    private CreditMapper clientMapper;

    //Llama a los méodos que están en el repositoey
    public Flux<Credit> findAll(){
        log.debug("findAll executed");
        return clientRepository.findAll();
    }

    public Mono<Credit> findById(String customerId){
        log.debug("findById executed {}", customerId);
        return clientRepository.findById(customerId);
    }
    //Se guarda en el repositorio
    public Mono<Credit> create(Credit credit){
        log.debug("create executed {}", credit);
        return clientRepository.save(credit);
    }
    public Mono<Credit> update(String creditId, Credit credit){
        log.debug("update executed {}:{}", creditId, credit);
        //Se busca por ID is existe se actualiza
        return clientRepository.findById(creditId)
                .flatMap(dbCustomer -> {
                    return clientRepository.save(dbCustomer);
                });
    }

    public Mono<Credit> delete(String customerId){
        log.debug("delete executed {}", customerId);
        return clientRepository.findById(customerId)
                .flatMap(existingCustomer -> clientRepository.delete(existingCustomer)
                        .then(Mono.just(existingCustomer)));
    }
}
