package com.nttdata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**Declarando las variables de la clase Client.*/
@Data
@Document(collection = "client")
public class Client {

  @Id
  private String id;
  
  private String name;
  
  private String type;
  
  @Field(name = "identity_number")
  private String identityNumber;
  
}
