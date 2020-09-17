package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends BaseEntity {

    @Column(name = "pedido_id")
    private Long pedidoId;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}
