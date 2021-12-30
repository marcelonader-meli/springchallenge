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

    public ProductCreateDTO convertToDTO(ProductEntity productEntity){

        this.productId = productEntity.getProductId();


        return this;
    }

}
