package com.nttdata.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

/**Declarando las variables de la clase Client.*/
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "client")
public class Client {

  @Id
  private String id;
  
  private String name;
  
  private String type;
  
  @Field(name = "identity_number")
  private String identityNumber;
  
}
