package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido extends BaseEntity {

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private BigDecimal quantidade;

}
