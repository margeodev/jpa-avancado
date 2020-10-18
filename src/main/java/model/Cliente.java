package model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity {

    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

}
