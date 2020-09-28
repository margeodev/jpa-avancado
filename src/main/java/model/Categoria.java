package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria extends BaseEntity {

    private String nome;

    @JoinColumn(name = "categoria_pai_id")
    @ManyToOne
    private Categoria categoriaPai;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;
}
