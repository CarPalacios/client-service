package com.nttdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "client")
public class Client {

	@Id
	private String id;
	
	private String name;
	
	private String type;
	
//	@Field(name = "identity_number")
	private String identityNumber;
	
}
