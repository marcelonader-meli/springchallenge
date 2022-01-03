package br.com.meli.springchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    private Long clientId;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String uf;


}
