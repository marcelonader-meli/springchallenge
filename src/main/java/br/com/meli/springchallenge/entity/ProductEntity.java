package br.com.meli.springchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity  implements Comparable<ProductEntity> {

    private Long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String pretige;

    @Override
    public int compareTo(ProductEntity productEntity) {
        return this.name.compareTo(productEntity.getName());
    }
}
