//package com.nttdata.service.impl;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import com.nttdata.model.Client;
//
//import reactor.core.publisher.Mono;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ClientServiceImplTest {
//  
//  @Autowired
//  private WebTestClient client;
//  
//  @Test
//  void testFindAll() {
//    client.get().uri("/client").accept(MediaType.APPLICATION_JSON)
//    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
//    .expectBodyList(Client.class).hasSize(6);
//  }
//
//  @Test
//  void testFindById() {
//      
//    client.get().uri("/client/6196f0aab3c9d3812f24bd70")
//    .accept(MediaType.APPLICATION_JSON).exchange()
//    .expectStatus().isOk()
//    .expectHeader().contentType(MediaType.APPLICATION_JSON)
//    .expectBody()
//    .jsonPath("$.id").isNotEmpty()
//    .jsonPath("$.name").isEqualTo("Carlos");
//  }
//
//  @Test
//  void testCreate() {
//    
//    Client clients = new Client("2","Juan","PERSONAL","72452304");
//    
//    client.post().uri("/client")
//    .contentType(MediaType.APPLICATION_JSON)
//    .accept(MediaType.APPLICATION_JSON)
//    .body(Mono.just(clients), Client.class)
//    .exchange()
//    .expectStatus().isCreated()
//    .expectHeader().contentType(MediaType.APPLICATION_JSON)
//    .expectBody()
//    .jsonPath("$.id").isNotEmpty()
//    .jsonPath("$.name").isEqualTo("Juan")
//    .jsonPath("$.type").isEqualTo("PERSONAL")
//    .jsonPath("$.identityNumber").isEqualTo("72452304");
//  }
//
//  @Test
//  void testUpdate() {
//    
//    Client clientedit = new Client();
//    clientedit.setName("Alejandro");
//    clientedit.setType("PERSONAL");
//    clientedit.setIdentityNumber("72451234");
//    
//    client.put().uri("/client/6196f14642e4ca32a9f5b199")
//    .contentType(MediaType.APPLICATION_JSON)
//    .accept(MediaType.APPLICATION_JSON)
//    .body(Mono.just(clientedit), Client.class)
//    .exchange()
//    .expectStatus().isOk()
//    .expectHeader().contentType(MediaType.APPLICATION_JSON)
//    .expectBody()
//    .jsonPath("$.id").isNotEmpty()
//    .jsonPath("$.name").isEqualTo("Alejandro")
//    .jsonPath("$.type").isEqualTo("PERSONAL")
//    .jsonPath("$.identityNumber").isEqualTo("72451234");
//  }
//
//  @Test
//  void testDelete() {
//        
//    client.delete().uri("/client/1")
//    .exchange()
//    .expectStatus().isNoContent()
//    .expectBody()
//    .isEmpty();
//        
//  }
//
//}
