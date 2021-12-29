package br.com.meli.springchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesPurchaseEntity {

    private Long productId;
    private String name;
    private String brand;
    private Integer quantity;

    public ArticlesPurchaseEntity(ProductEntity productEntity, Integer quantity){
        this.productId = productEntity.getProductId();
        this.name = productEntity.getName();
        this.brand = productEntity.getBrand();
        this.quantity = quantity;
    }

}
