package com.nttdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.model.Client;
import com.nttdata.repository.ClientRepository;
import com.nttdata.service.impl.ClientServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(ClientServiceImpl.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ClientController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class ClientControllerTest {

  @Autowired
  private WebTestClient client;
  
  @MockBean
  private ClientRepository repository;
  
  @Test
  void testFindAll() {
    
    Client clients = new Client("4","Jazmin","PERSONAL","72452305");
    Client clients2 = new Client("5","Jazmin","PERSONAL","72452306");    
    List<Client> list = new ArrayList<>();
    list.add(clients);
    list.add(clients2);
    Flux<Client> fluxclient = Flux.fromIterable(list);
    
    Mockito.when(repository.findAll()).thenReturn(fluxclient);
    client.get().uri("/client").accept(MediaType.APPLICATION_JSON)
    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBodyList(Client.class).hasSize(2);
  }

  @Test
  void testFindById() {
    
    Client clients = new Client("4","Jazmin","PERSONAL","72452305");
    
    Mockito.when(repository.findById("4")).thenReturn(Mono.just(clients));
    client.get().uri("/client/4")
    .accept(MediaType.APPLICATION_JSON).exchange()
    .expectStatus().isOk()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.name").isEqualTo("Jazmin");
  }

  @Test
  void testCreate() {
    
    Client clients = new Client("6","Yareli","PERSONAL","72451234");
    
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(clients));
    client.post().uri("/client")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(clients), Client.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.name").isEqualTo("Yareli")
    .jsonPath("$.type").isEqualTo("PERSONAL")
    .jsonPath("$.identityNumber").isEqualTo("72451234");
    
  }

  @Test
  void testUpdate() {
    Client clients = new Client("7","Yareli","PERSONAL","72451235");
    
    Mockito.when(repository.findById("7")).thenReturn(Mono.just(clients));
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(clients));
    client.put().uri("/client/7")
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(clients), Client.class)
    .exchange()
    .expectStatus().isOk()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.name").isEqualTo("Yareli")
    .jsonPath("$.type").isEqualTo("PERSONAL")
    .jsonPath("$.identityNumber").isEqualTo("72451235");
  }

  @Test
  void testDeleteById() {
    Client clients = new Client("8","Yareli","PERSONAL","72451235");
    
    Mockito.when(repository.findById("8")).thenReturn(Mono.just(clients));
    Mockito.when(repository.deleteById("8")).thenReturn(Mono.empty());
    client.delete().uri("/client/8")
    .exchange()
    .expectStatus().isNoContent()
    .expectBody()
    .isEmpty();
  }

}
