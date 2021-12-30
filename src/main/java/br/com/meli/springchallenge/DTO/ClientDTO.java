package br.com.meli.springchallenge.DTO;


import br.com.meli.springchallenge.entity.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String uf;

    public static ClientEntity convert(ClientDTO dto) {
        return ClientEntity.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .uf(dto.getUf())
                .build();
    }

    public static ClientDTO convert(ClientEntity clientEntity) {
        return ClientDTO.builder()
                .name(clientEntity.getName())
                .age(clientEntity.getAge())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())
                .uf(clientEntity.getUf())
                .build();
    }

    public static List<ClientDTO> convert(List<ClientEntity> clients){
        return clients.stream().map(u -> convert(u)).collect(Collectors.toList());
    }

}
