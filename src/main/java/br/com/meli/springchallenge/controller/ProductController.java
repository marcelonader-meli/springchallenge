package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProductEntity>> findAll() {
        try {
            return ResponseEntity.ok().body(productService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/teste")
    public ResponseEntity<?> listProductByCategory(@RequestParam String category) {
        return null;
    }

    @GetMapping(value = "/teste2")
    public ResponseEntity<?> listProductsFiltered(@RequestParam String category, @RequestParam Boolean freeShipping, @RequestParam String brand, @RequestParam Double price, @RequestParam String prestige, @RequestParam Integer order) {
        return null;
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<?> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) {
        return null;
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductEntity>> saveProduct(@RequestBody List<ProductEntity> listProducts) {
        try {
            return ResponseEntity.ok().body(productService.saveProduct(listProducts));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
