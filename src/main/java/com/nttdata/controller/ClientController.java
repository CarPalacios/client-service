package com.nttdata.controller;

import com.nttdata.model.Client;
import com.nttdata.service.ClientService;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**Dentro de la clase ClientController se realizan los metodos http.*/
@RestController
@RequestMapping(value = "/client")
public class ClientController {

  @Autowired
  private ClientService service;
  
  /** Muestra los registros de la tabla * @return registro de la tabla seleccionada. */
  @GetMapping
  public Mono<ResponseEntity<Flux<Client>>> findAll() {
    
    Flux<Client> clients = service.findAll();
    return Mono.just(ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(clients));              
    
  }
  
  /** Muestra los registros de la tabla a traves de un id.
   * * @return registro de la tabla seleccionada por id. */
  
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Client>> findById(@PathVariable("id") String id) {
  
    return service.findById(id)
        .map(c -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));    
  }
  
  /** Crea los registros de la tabla.* @return crea registros de la tabla. */
  @PostMapping
  public Mono<ResponseEntity<Client>> 
      create(@RequestBody Client client, final ServerHttpRequest request) {
    
    return service.create(client)
        .map(c -> ResponseEntity
            .created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
    
  }
  
  /** Actualiza los registros de la tabla.* @return actualiza registros de la tabla. */
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Client>> update(@PathVariable String id, @RequestBody Client client) {
    
    Mono<Client> monoDatabase = service.findById(id);
    
    Mono<Client> monoClient = Mono.just(client);
    
    return monoDatabase
        .zipWith(monoClient, (db, cl) -> {
          db.setId(id);
          db.setName(cl.getName());
          db.setType(cl.getType());
          db.setIdentityNumber(cl.getIdentityNumber());          
          return db;
        })
        .flatMap(service::update)
        .map(c -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(c))
        .defaultIfEmpty(new ResponseEntity<Client>(HttpStatus.NOT_FOUND));
        
  }
  
  /** Elimina los registros de la tabla.* @return elimina registros de la tabla. */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id) {
    
    return service.findById(id)
        .flatMap(c -> {
          return service.delete(c.getId())
              .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
        })
        .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  
  }
  
  
  
}
