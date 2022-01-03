package br.com.meli.springchallenge.service;

import br.com.meli.springchallenge.entity.ClientEntity;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.exceptions.ExistingClientException;
import br.com.meli.springchallenge.exceptions.IncompleteDataException;
import br.com.meli.springchallenge.exceptions.ListIsEmptyException;
import br.com.meli.springchallenge.repository.ClientRepository;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void registerClient(ClientEntity clientEntity) throws IOException, IncompleteDataException, ExistingClientException {
        List<Object> listAttrs = Arrays.asList(clientEntity.getName(), clientEntity.getPhone(), clientEntity.getEmail(), clientEntity.getUf());
        if(listAttrs.contains(null) || listAttrs.contains("".trim()))
            throw new IncompleteDataException("Verifique se todos os campos estão preenchidos corretamente, conforme a seguir: \n\"name\": \"string\",\n" +
                    "\"age\": 0,\n" +
                    "\"phone\": \"string\",\n" +
                    "\"email\": \"string\",\n" +
                    "\"uf\": \"string\"");
        try {
            ClientEntity clientEntityRepository = clientRepository.findOneByNameAndEmail(clientEntity.getName(), clientEntity.getEmail());
            if(clientEntityRepository != null)
                throw new ExistingClientException("Cliente existente.");
        } catch (MismatchedInputException e) {
            e.printStackTrace();
        }
        clientRepository.save(clientEntity);
    }

    public List<ClientEntity> listAllByState(String uf) throws IOException, ListIsEmptyException {
        return clientRepository.listAllByState(uf);
    }

    public List<ClientEntity> listAll() throws IOException, ListIsEmptyException {
        return clientRepository.listAll();
    }

}
