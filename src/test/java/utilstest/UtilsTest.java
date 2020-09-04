package utilstest;

import model.Cliente;
import model.Produto;

import java.math.BigDecimal;

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
                .build();
    }
}
