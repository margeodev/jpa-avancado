package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Long pedidoId;

    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long produtoId;

}

