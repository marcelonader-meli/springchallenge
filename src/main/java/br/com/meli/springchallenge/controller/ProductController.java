package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProductEntity>> findAll() throws IOException {
        return ResponseEntity.ok(productService.listAll());
    }
//
//    @GetMapping
//    public ResponseEntity<?> listProductByCategory(@RequestParam String category){
//        return null;
//    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listProductsFiltered(
//            @RequestParam(required = false) Integer order,
            ProductEntity productEntity
    ) throws IOException {
        return ResponseEntity.ok(productService.applyFilters(productEntity));
    }

    @PostMapping("/register")
    public ResponseEntity<ProductEntity> registerProduct(@RequestBody ProductEntity productEntity) throws IOException {
        return ResponseEntity.ok(productService.registerProduct(productEntity));
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<?> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) {
        return null;
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductEntity>> saveProduct(@RequestBody List<ProductEntity> listProducts) {
        try {
            return ResponseEntity.ok().body(productService.saveProducts(listProducts));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
