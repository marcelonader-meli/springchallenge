package br.com.meli.springchallenge.DTO;

import br.com.meli.springchallenge.entity.ProductEntity;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDTO {

    private Long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;

    public static ProductDTO convert(ProductEntity productEntity){
    return ProductDTO.builder()
            .productId(productEntity.getProductId())
            .name(productEntity.getName())
            .category(productEntity.getCategory())
            .brand(productEntity.getBrand())
            .price(productEntity.getPrice())
            .quantity(productEntity.getQuantity())
            .freeShipping(productEntity.getFreeShipping())
            .prestige(productEntity.getPrestige())
            .build();
    }
}
