package utilstest;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class UtilsTest {

    public static Produto buildProduto() {
        return Produto.builder()
                .nome("Câmera Canon")
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAtualizacao(LocalDateTime.now())
                .descricao("A melhor definicao para suas fotos")
                .preco(new BigDecimal(5000))
                .build();
    }

    public static Cliente buildCliente() {
        return Cliente.builder()
                .nome("Maria Pereira")
                .sexo(SexoCliente.FEMININO)
                .build();
    }

    public static Pedido buildPedido() {
        return Pedido.builder()
                .status(StatusPedido.AGUARDANDO)
                .enderecoEntrega(buildEndereco())
                .build();
    }

    public static Pedido buildPedidoCustom(Cliente cliente, Produto produto) {
        return Pedido.builder()
                .cliente(cliente)
                .total(produto.getPreco())
                .dataCriacao(LocalDateTime.now())
                .status(StatusPedido.AGUARDANDO)
                .build();
    }

//    public static ItemPedido buildItemPedido(Pedido pedido, Produto produto) {
//        return ItemPedido.builder()
//                .pedidoId(pedido.getId())
//                .produtoId(produto.getId())
//                .pedido(pedido)
//                .produto(produto)
//                .precoProduto(produto.getPreco())
//                .quantidade(BigDecimal.ONE)
//                .build();
//    }

    public static NotaFiscal buildNotaFiscal(Pedido pedido) {
        return NotaFiscal.builder()
                .xml("TESTE")
                .dataEmissao(new Date())
                .pedido(pedido)
                .build();
    }

    public static PagamentoCartao buildPagamentoCartao(Pedido pedido) {
        return PagamentoCartao.builder()
                .numero("1234")
                .status(StatusPagamento.PROCESSANDO)
                .pedido(pedido)
                .build();
    }

    private static EnderecoEntrega buildEndereco() {
        return EnderecoEntrega.builder()
                .bairro("Centro")
                .cep("58000")
                .cidade("João Pessoa")
                .estado("Paraiba")
                .logradouro("Av. Projetada")
                .build();
    }
}
