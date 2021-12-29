package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {



    private List<ProductEntity> productList = new ArrayList<>();


    public List<ProductEntity> listAll(){
        return this.productList;
    }

    public ProductEntity findOneById(Long productId){
        return productList.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(new ProductEntity());
    }

    public void save(ProductEntity productEntity){
        this.productList.add(productEntity);
    }

    public void removeById(Long productId){

        ProductEntity productEntity = findOneById(productId);

        this.productList.remove(productEntity);
    }

    public void orderByASC(List<ProductEntity> productList) {
        Collections.sort(productList);
    }


}
