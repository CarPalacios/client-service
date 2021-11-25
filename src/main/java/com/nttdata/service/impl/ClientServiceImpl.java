package com.nttdata.service.impl;

import com.nttdata.model.Client;
import com.nttdata.repository.ClientRepository;
import com.nttdata.service.ClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**La clase ClientServiceImpl implementa el ClientService.*/
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository repo;
  
  @Override
  public Flux<Client> findAll() {
    return repo.findAll();
  }

  @Override
  @CircuitBreaker(name = "clientCB", fallbackMethod = "clientFallback")
  public Mono<Client> findById(String id) {
    return repo.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("No existe el Cliente.")));
  }

  @Override
  public Mono<Client> create(Client client) {
    return repo.save(client);
  }

  @Override
  public Mono<Client> update(Client client) {
    return repo.save(client);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }
  
  public Mono<Client> clientFallback(String id, Exception ex) {  
    return Mono.just(Client.builder().id(id).name(ex.getMessage()).build()); 
  }

}
