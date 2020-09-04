package iniciandocomjpa;

import model.Produto;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

public class BuscaObjetoPorIdentificadorTest extends EntityManagerTest {

    @Test
    public void buscaPorId() {
        Long id = 1L;
        Produto produto = entityManager.find(Produto.class, id);

        Assert.assertEquals(id, produto.getId());
    }

    @Test
    public void atualizarPorReferenciaTest() {
        Produto produto = entityManager.find(Produto.class, 1L);
        String nomeProd = produto.getNome();
        String nomeNovo = "Nova descricao";
        produto.setNome(nomeNovo);

        entityManager.refresh(produto);
        Assert.assertEquals(nomeProd, produto.getNome());
    }
}
