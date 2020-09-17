package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends BaseEntity {

    @Column(name = "pedido_id")
    private Long pedidoId;

    private StatusPagamento status;

    private String numero;

}
