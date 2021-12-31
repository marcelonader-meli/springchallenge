package br.com.meli.springchallenge.DTO;

import br.com.meli.springchallenge.entity.ProductEntity;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCreateDTO {

    private Long productId;
    private String name;
    private Integer quantity;

    public static ProductCreateDTO convertToDTO(ProductEntity productEntity){
//        productId = productEntity.getProductId();
//
//
//        return this;
        return ProductCreateDTO.builder()
                .productId(productEntity.getProductId())
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .build();
    }

}
