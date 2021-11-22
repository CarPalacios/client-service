package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Client;

//@NoRepositoryBean
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

//	crud		Metodo		Status Code
//	create		post		201 create
//	read		get			200 ok
//	update		put			200 ok
//	delete		delete		204 no content
	
}
