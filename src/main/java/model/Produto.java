package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto extends BaseEntity {

    private String nome;

    private String descricao;

    private BigDecimal preco;

}
