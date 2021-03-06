package br.com.meli.springchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    private Long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;


}
