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
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends BaseEntity {

    @Column(name = "pedido_id")
    private Long pedidoId;

    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;

}
