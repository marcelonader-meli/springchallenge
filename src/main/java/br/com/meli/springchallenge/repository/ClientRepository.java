package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ClientEntity;
import br.com.meli.springchallenge.exceptions.ExistingClientException;
import br.com.meli.springchallenge.exceptions.ListIsEmptyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
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

    public List<ClientEntity> listAll() throws IOException, ListIsEmptyException {
        try {
            List<ClientEntity> listAll =
                    Arrays.stream(mapper.readValue(Paths.get(this.PATH).toFile(), ClientEntity[].class))
                            .collect(Collectors.toList());
            return listAll;
        } catch (MismatchedInputException e) {
            throw new ListIsEmptyException();
        }
    }

    public List<ClientEntity> listAllByState(String uf) throws IOException, ListIsEmptyException {
        try {
            List<ClientEntity> listAllByState = Arrays.stream(mapper.readValue(Paths.get(this.PATH).toFile(), ClientEntity[].class))
                    .filter(c -> c.getUf().equalsIgnoreCase(uf)).collect(Collectors.toList());
            return listAllByState;
        } catch (MismatchedInputException e){
            throw new ListIsEmptyException();
        }
    }

    public ClientEntity findOneById(Long clientId) throws IOException {
        return Arrays.stream(mapper.readValue(Paths.get(this.PATH).toFile(), ClientEntity[].class))
                .filter(p -> p.getClientId().equals(clientId)).findFirst().orElse(new ClientEntity());
    }

    public void save(ClientEntity clientEntity) throws IOException {
        this.createFile();
        clientEntity.setClientId((long) clientList.size()+1);
        clientList.add(clientEntity);
        mapper.writeValue(new File(PATH), clientList);
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
