package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ProductController {

    @GetMapping("/")
    public ResponseEntity<?> listAll(){
        return null;
    }

    @GetMapping
    public ResponseEntity<?> listProductByCategory(@RequestParam String category){
        return null;
    }

    @GetMapping
    public ResponseEntity<?> listProductsFiltered(@RequestParam String category, @RequestParam Boolean freeShipping, @RequestParam String brand, @RequestParam Double price, @RequestParam String prestige, @RequestParam Integer order){
        return null;
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<?> buyProduct(@RequestBody ShoppingCartEntity shoppingCart){
        return null;
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<?> buyProduct(@RequestBody List<ProductEntity> listProducts) {
        return null;
    }

}
