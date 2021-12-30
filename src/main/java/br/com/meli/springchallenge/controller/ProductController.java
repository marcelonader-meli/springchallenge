package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.DTO.ProductCreateDTO;
import br.com.meli.springchallenge.DTO.TicketDTO;
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
    public ResponseEntity<List<ProductEntity>> listProductsFiltered(
//            @RequestParam(required = false) Integer order,
            ProductEntity productEntity
    ) throws IOException {
        return ResponseEntity.ok(productService.applyFilters(productEntity));
    }

    @GetMapping("/order{order}")
    public ResponseEntity<List<ProductEntity>> listOrderedProducts(@RequestParam(required = false) Integer order) throws IOException {
        return ResponseEntity.ok(productService.orderProducts(order));
    }


    @PostMapping("/register")
    public ResponseEntity<ProductEntity> registerProduct(@RequestBody ProductEntity productEntity) throws IOException {
        return ResponseEntity.ok(productService.registerProduct(productEntity));
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<TicketDTO> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) throws Exception {
        return ResponseEntity.ok(this.productService.buyProduct(shoppingCart));
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductCreateDTO>> saveProducts(@RequestBody List<ProductEntity> listProducts) {
        try {
            return ResponseEntity.ok().body(productService.saveProducts(listProducts));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
