package model;

import listener.GenericListener;
import listener.GerarNotaFiscalListener;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({ GerarNotaFiscalListener.class, GenericListener.class })
@Entity
@Table(name = "pedido")
public class Pedido extends BaseEntity {

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntrega enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    private void calcularTotal() {
        if(Objects.nonNull(itens)) {
            total = itens.stream().map(i -> i.getQuantidade().multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }
}
