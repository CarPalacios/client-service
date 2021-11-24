package com.nttdata.repository;

import com.nttdata.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//@NoRepositoryBean
/** El repositorio client hace una herencia a ReactiveMongoRepository. */
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

//  crud    Metodo    Status Code
//  create    post    201 create
//  read    get      200 ok
//  update    put      200 ok
//  delete    delete    204 no content
  
}
