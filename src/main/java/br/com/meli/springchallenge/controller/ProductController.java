package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.DTO.ProductCreateDTO;
import br.com.meli.springchallenge.DTO.ProductDTO;
import br.com.meli.springchallenge.DTO.TicketDTO;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.exceptions.ListIsEmptyException;
import br.com.meli.springchallenge.exceptions.ProductNotFoundException;
import br.com.meli.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> listAll() throws IOException {
        try {
            return ResponseEntity.ok(productService.listAll().stream().map(ProductDTO::convert).collect(Collectors.toList()));
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ListIsEmptyException e){
            return ResponseEntity.badRequest().body("Não há produtos cadastrados no sistema.");
        }
    }

    @GetMapping
    public ResponseEntity<?> listProductsFiltered(
            @RequestParam(required = false) Integer order,
            ProductEntity productEntity
    ) throws IOException, ProductNotFoundException, ListIsEmptyException {
        try {
            List<ProductEntity> listFiltered = productService.applyFilters(productEntity);
            List<ProductEntity> listAll = productService.listAll();
            if (order != null && productEntity != null) {
                productService.orderProducts(order, listFiltered);
                return ResponseEntity.ok(listFiltered.stream().map(ProductDTO::convert).collect(Collectors.toList()));
            } else if (order != null) {
                productService.orderProducts(order, listAll);
                return ResponseEntity.ok(listAll.stream().map(ProductDTO::convert).collect(Collectors.toList()));
            }
            return ResponseEntity.ok(listFiltered.stream().map(ProductDTO::convert).collect(Collectors.toList()));

            /*return ResponseEntity.ok(productService.applyFilters(productEntity));*/
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ListIsEmptyException e){
            return ResponseEntity.badRequest().body("Não há produtos cadastrados no sistema.");
        }

    }


    @PostMapping("/register")
    public ResponseEntity<ProductEntity> registerProduct(@RequestBody ProductEntity productEntity) throws
            IOException {
        try {
            return ResponseEntity.ok(productService.registerProduct(productEntity));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<?> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) throws Exception {
        try {
            TicketDTO ticketDTO = this.productService.buyProduct(shoppingCart);
            if(ticketDTO.getObservacoes().equals("")){
                return ResponseEntity.ok(ticketDTO);
            }else{

                Map<String, Object> map = new HashMap<>();

                map.put("message", "Não foi possivel adicionar todos os itens ao carrinho, verifique as observacoes no ticket");
                map.put("Ticket",ticketDTO);

                return ResponseEntity.ok().body(map);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/insert-articles-request")
    public ResponseEntity<?> saveProducts(@RequestBody List<ProductEntity> listProducts) throws IOException{
        try {
            if (listProducts.size() > 0){
                return ResponseEntity.ok().body(productService.saveProducts(listProducts));
            } else {
                return ResponseEntity.badRequest().body("Por favor, informe os produtos a serem cadastrados!");
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
