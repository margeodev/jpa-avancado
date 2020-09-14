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
public class Cliente extends BaseEntity {

    private String nome;

    private SexoCliente sexo;

}
