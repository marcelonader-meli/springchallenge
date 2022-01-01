package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.DTO.ClientDTO;
import br.com.meli.springchallenge.DTO.ProductCreateDTO;
import br.com.meli.springchallenge.DTO.TicketDTO;
import br.com.meli.springchallenge.entity.ClientEntity;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.exceptions.ExistingClientException;
import br.com.meli.springchallenge.exceptions.IncompleteDataException;
import br.com.meli.springchallenge.exceptions.ListIsEmptyException;
import br.com.meli.springchallenge.exceptions.ProductNotFoundException;
import br.com.meli.springchallenge.service.ClientService;
import br.com.meli.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping("/register")
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientEntity clientEntity) throws IOException, IncompleteDataException, ExistingClientException {
        clientService.registerClient(clientEntity);
        return ResponseEntity.ok(ClientDTO.convert(clientEntity));
    }


}