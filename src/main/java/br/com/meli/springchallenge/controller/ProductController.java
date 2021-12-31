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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductEntity>> listAll() throws IOException {
        try {
            return ResponseEntity.ok(productService.listAll());
        } catch (IOException | ListIsEmptyException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProductsFiltered(
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
        } catch (IOException |
                ListIsEmptyException e) {
            return ResponseEntity.badRequest().body(null);
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
    public ResponseEntity<TicketDTO> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) throws Exception {
        try {
            return ResponseEntity.ok(this.productService.buyProduct(shoppingCart));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        //if(observacoes.equals("")){
        //    throw new Exception(" Não foi possivel adicionar todos os itens ao carrinho, verifique as observacoes no ticket");
        //}
    }


    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductCreateDTO>> saveProducts(@RequestBody List<ProductEntity> listProducts) {
        try {
            return ResponseEntity.ok().body(productService.saveProducts(listProducts));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
