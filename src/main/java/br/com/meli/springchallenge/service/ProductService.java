package br.com.meli.springchallenge.service;

import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<ProductEntity> orderingAscOrder(List<ProductEntity> listProduct) {
        productRepository.orderByASC(listProduct);
        return listProduct;
    }


}
