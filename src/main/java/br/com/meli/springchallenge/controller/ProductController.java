package br.com.meli.springchallenge.controller;

import br.com.meli.springchallenge.DTO.ProductCreateDTO;
import br.com.meli.springchallenge.DTO.TicketDTO;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.exceptions.ProductNotFoundException;
import br.com.meli.springchallenge.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
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

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listProductsFiltered(
            @RequestParam(required = false) Integer order,
            ProductEntity productEntity
    ) throws IOException, ProductNotFoundException {
        List<ProductEntity> listFiltered = productService.applyFilters(productEntity);
        List<ProductEntity> listAll = productService.listAll();
        if(order != null && productEntity != null) {
            productService.orderProducts(order, listFiltered);
            return ResponseEntity.ok(listFiltered);
        } else if(order != null) {
            productService.orderProducts(order, listAll);
            return ResponseEntity.ok(listAll);
        }
        return ResponseEntity.ok(listFiltered);
    }

//    @GetMapping("/order{order}")
//    public ResponseEntity<List<ProductEntity>> listOrderedProducts(@RequestParam(required = false) Integer order) throws IOException {
//        return ResponseEntity.ok(productService.orderProducts(order));
//    }


    @PostMapping("/register")
    public ResponseEntity<ProductEntity> registerProduct(@RequestBody ProductEntity productEntity) throws IOException {
        return ResponseEntity.ok(productService.registerProduct(productEntity));
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<TicketDTO> buyProduct(@RequestBody ShoppingCartEntity shoppingCart) throws Exception {
        return ResponseEntity.ok(this.productService.buyProduct(shoppingCart));
        //if(observacoes.equals("")){
        //    throw new Exception(" Não foi possivel adicionar todos os itens ao carrinho, verifique as observacoes no ticket");
        //}
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
