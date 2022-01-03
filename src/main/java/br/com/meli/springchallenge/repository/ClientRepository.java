package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ClientEntity;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.exceptions.ExistingClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final String PATH = "src/main/resources/clients.json";

    public ClientEntity findOneById(Long clientId) throws IOException {
        return Arrays.stream(mapper.readValue(Paths.get(this.PATH).toFile(), ClientEntity[].class))
                .filter(p -> p.getClientId().equals(clientId)).findFirst().orElse(new ClientEntity());
    }

    public void save(ClientEntity clientEntity) throws IOException {
        this.createFile();
        clientEntity.setClientId((long) clientList.size()+1);
        clientList.add(clientEntity);

    }

    public ClientEntity findOneByNameAndEmail(String name, String email) throws IOException, ExistingClientException {
        this.createFile();
        return Arrays.stream(mapper.readValue(Paths.get(this.PATH).toFile(), ClientEntity[].class))
                .filter(p -> p.getName().equalsIgnoreCase(name) && p.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public void createFile() throws IOException {
        mapper.writeValue(new File(this.PATH), clientList);
    }

}
