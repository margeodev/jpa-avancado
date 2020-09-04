package iniciandocomjpa;

import model.Produto;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildProduto;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirPrimeiroObjeto() {
        Produto produto = buildProduto();
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto verificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals(produto, verificacao);
    }

    @Test
    public void atualizarObjetoPorMergeTest() {
        Produto produto = buildProduto();
        produto.setId(1L);
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto verificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals(produto.getDescricao(), verificacao.getDescricao());
    }

    @Test
    public void inserirObjetoPorMergeTest() {
        Produto produto = buildProduto();
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        Produto verificacao = entityManager.find(Produto.class, 2L);
        Assert.assertNotNull(verificacao);
    }

    @Test
    public void atualizarObjetoGerenciadoTest() {
        Produto produto = entityManager.find(Produto.class, 1L);
        String novaDescricao = "Nova descrição";
        produto.setDescricao(novaDescricao);
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto verificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals(novaDescricao, verificacao.getDescricao());
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto verificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNull(verificacao);
    }
}
