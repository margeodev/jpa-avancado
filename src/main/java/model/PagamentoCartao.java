package model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagamentoCartao extends BaseEntity {

    private Long pedidoId;

    private StatusPagamento status;

    private String numero;

}
