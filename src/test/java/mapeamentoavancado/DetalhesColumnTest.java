package mapeamentoavancado;

import model.Produto;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static utilstest.UtilsTest.buildProduto;

public class DetalhesColumnTest extends EntityManagerTest {

    @Test
    public void impedirInsercaoDaColunaAtualizacao() {
        Produto produto = buildProduto();

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao.getDataCriacao());
        Assert.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
    }

    @Test
    public void impedirAtualizacaoDaColunaCriacao() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1L);
        produto.setPreco(BigDecimal.TEN);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
        Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}
