package model;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedido extends BaseEntity {

    private Long pedidoId;

    private Long produtoId;

    private BigDecimal precoProduto;

    private BigDecimal quantidade;

}
