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
public class Pedido extends BaseEntity {

    private LocalDateTime dataPedido;

    private LocalDateTime dataConclusao;

    private Long notafiscalId;

    private BigDecimal total;

    private StatusPedido status;

}
