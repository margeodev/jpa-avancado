package model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria extends BaseEntity {

    private String nome;

    private Long categoriaPaiId;

}
