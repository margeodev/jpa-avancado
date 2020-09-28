package utilstest;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class UtilsTest {

    public static Produto buildProduto() {
        return Produto.builder()
                .nome("Câmera Canon")
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
                .dataPedido(LocalDateTime.now())
                .status(StatusPedido.AGUARDANDO)
                .total(new BigDecimal(1000))
                .enderecoEntrega(buildEndereco())
                .build();
    }

    public static PagamentoCartao buildPagamentoCartao(Pedido pedido) {
        return PagamentoCartao.builder()
                .numero("1234")
                .status(StatusPagamento.PROCESSANDO)
                .pedido(pedido)
                .build();
    }

    public static NotaFiscal buildNotaFiscal(Pedido pedido) {
        return NotaFiscal.builder()
                .xml("TESTE")
                .dataEmissao(new Date())
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
