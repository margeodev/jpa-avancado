package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
public class Cliente extends BaseEntity {

    private String nome;

    @Column(table = "cliente_detalhe")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    private String cpf;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

}
