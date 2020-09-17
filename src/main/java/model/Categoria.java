package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria extends BaseEntity {

    private String nome;

    @Column(name = "categoria_pai_id")
    private Long categoriaPaiId;

}
