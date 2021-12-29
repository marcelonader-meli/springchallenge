package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;

import java.util.List;

@Repository
public class ProductRepository {

    private List<ProductEntity> productList = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "products.json";

    public List<ProductEntity> listAll() throws IOException {
        return Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));

    }

    public ProductEntity findOneById(Long productId) throws IOException {
        return Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class))
                .stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(new ProductEntity());
    }

    public void save(ProductEntity productEntity) throws IOException {
        productEntity.setProductId((long) productList.size()+1);
        productList.add(productEntity);
        mapper.writeValue(new File(PATH), productList);
    }

    public void saveAll(List<ProductEntity> products) throws IOException {
        //fazer um foreach e no id inserir o productList.size() + o counter
        Integer counter = 1;
        long lastId = productList.stream().mapToLong(item -> item.getProductId()).max().orElse(0);
        for (ProductEntity product : products) {
            product.setProductId(lastId + counter);
        }

        productList.addAll(products);
        mapper.writeValue(new File(PATH), productList);
    }

    public void removeById(Long productId) throws IOException {
        List<ProductEntity> productListAll = Arrays.asList(mapper.readValue(Paths.get("products.json")
                .toFile(), ProductEntity[].class));
        ProductEntity productEntity = findOneById(productId);
        productListAll.remove(productEntity);
        mapper.writeValue(new File(PATH), productListAll);
    }



    public void orderByASC(List<ProductEntity> productList) {
        Collections.sort(productList);
    }


}

