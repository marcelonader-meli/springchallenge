package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ClientEntity;
import br.com.meli.springchallenge.entity.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private final List<ClientEntity> clientList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "clients.json";

    public List<ClientEntity> listAll() throws IOException {
        return Arrays.asList(mapper.readValue(Paths.get("clients.json").toFile(), ClientEntity[].class));

    }

    public ClientEntity findOneById(Long clientId) throws IOException {
        return Arrays.stream(mapper.readValue(Paths.get("clients.json").toFile(), ClientEntity[].class))
                .filter(p -> p.getClientId().equals(clientId)).findFirst().orElse(new ClientEntity());
    }

    public void save(ClientEntity clientEntity) throws IOException {
        clientEntity.setClientId((long) clientList.size()+1);
        clientList.add(clientEntity);
        mapper.writeValue(new File(PATH), clientList);
    }
    public List<ClientEntity> findClientsByUF(String uf) throws IOException {
        return Arrays.stream(mapper.readValue(Paths.get("clients.json").toFile(), ClientEntity[].class))
                .filter(p -> p.getUf().equals(uf)).collect(Collectors.toList());
    }

}
