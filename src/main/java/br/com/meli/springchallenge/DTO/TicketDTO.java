package br.com.meli.springchallenge.DTO;

import br.com.meli.springchallenge.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private static Long cont =1000L;

    private Long id;
    private List<ProductEntity>  articles;
    private String observacoes;
    private BigDecimal total;

    public static Long getCont(){
        return cont++;
    }

}
