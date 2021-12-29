package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ProductController<ProductEntity> {


    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductEntity>> listAll() throws IOException {
        return ResponseEntity.ok(productService.listAll());
    }
//
//    @GetMapping
//    public ResponseEntity<?> listProductByCategory(@RequestParam String category){
//        return null;
//    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listProductsOrdered(
            @RequestParam Integer order) throws IOException {
        return ResponseEntity.ok(productService.orderingAscOrder(productEntity));
    }

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
    public ResponseEntity<?> buyProduct(@RequestBody ShoppingCartEntity shoppingCart){
        return null;
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<?> buyProduct(@RequestBody List<ProductEntity> listProducts) {
        return null;
    }

}
