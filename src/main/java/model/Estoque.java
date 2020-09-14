package model;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque extends BaseEntity {

    private Long produtoId;

    private BigDecimal quantidade;

}
