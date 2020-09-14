package model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagamentoBoleto extends BaseEntity {

    private Long pedidoId;

    private StatusPagamento status;

    private String codigoBarras;

}
