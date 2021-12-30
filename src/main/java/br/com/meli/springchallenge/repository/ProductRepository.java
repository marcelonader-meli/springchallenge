package br.com.meli.springchallenge.repository;

import br.com.meli.springchallenge.entity.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Repository
public class ProductRepository {



    private final List<ProductEntity> productList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "products.json";

    public List<ProductEntity> listAll() throws IOException {

        return Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));

    }

    public ProductEntity findOneById(Long productId) throws IOException {
        return Arrays.stream(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class))
                .filter(p -> p.getProductId().equals(productId)).findFirst().orElse(new ProductEntity());
    }

    public void save(ProductEntity productEntity) throws IOException {

        if(productEntity.getProductId()!=null){
            for(ProductEntity p : productList){
                if(p.getProductId()==productEntity.getProductId()){
                    p.setQuantity(productEntity.getQuantity());
                }
            }
        }else{
            productEntity.setProductId((long) productList.size()+1);
            productList.add(productEntity);
        }

        mapper.writeValue(new File(PATH), productList);
    }

    public void saveAll(List<ProductEntity> products) throws IOException {
        Integer counter = 1;
        long lastId = productList.stream().mapToLong(item -> item.getProductId()).max().orElse(0);
        for (ProductEntity product : products) {
            product.setProductId(lastId + counter++);
            productList.add(product);
        }
        mapper.writeValue(new File(PATH), productList);
    }

    public void removeById(Long productId) throws IOException {
        List<ProductEntity> productListAll = Arrays.asList(mapper.readValue(Paths.get("products.json")
                .toFile(), ProductEntity[].class));
        ProductEntity productEntity = findOneById(productId);
        productListAll.remove(productEntity);
        mapper.writeValue(new File(PATH), productListAll);
    }

    public List<ProductEntity> sortByAscName() throws IOException{
        List<ProductEntity> listProducts = Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));
        Collections.sort(listProducts, (a, b) -> a.getName().compareTo(b.getName()));
        return listProducts;

    }

    public List<ProductEntity> sortByDescName() throws IOException{
        List<ProductEntity> listProducts = Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));
        Collections.sort(listProducts, (a, b) -> b.getName().compareTo(a.getName()));
        return listProducts;

    }

    public List<ProductEntity> orderByLowestPrice() throws IOException {
        List<ProductEntity> listProducts = Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));
        Collections.sort(listProducts, (a, b) -> a.getPrice().compareTo(b.getPrice()));
        return listProducts;
    }


    public List<ProductEntity> orderByTheHighestPrice() throws IOException {
        List<ProductEntity> listProducts = Arrays.asList(mapper.readValue(Paths.get("products.json").toFile(), ProductEntity[].class));
        Collections.sort(listProducts, (a, b) -> b.getPrice().compareTo(a.getPrice()));
        return listProducts;
    }
}

