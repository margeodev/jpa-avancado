package model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotaFiscal extends BaseEntity {

    private Long pedidoId;

    private String xml;

    private Date dataEmissao;

}
