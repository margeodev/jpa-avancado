package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estoque")
public class Estoque extends BaseEntity {

    @Column(name = "produto_id")
    private Long produtoId;

    private BigDecimal quantidade;

}
