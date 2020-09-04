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
public class Produto extends BaseEntity {

    private String nome;

    private String descricao;

    private BigDecimal preco;

}
