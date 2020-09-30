package relacionamentos;

import model.Categoria;
import model.Produto;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.util.Arrays;

public class ManyToManyTest extends EntityManagerTest {

    @Test
    public void testeRelacionamento() {
        Produto produto = entityManager.find(Produto.class, 1L);
        Categoria categoria = entityManager.find(Categoria.class, 1L);

        entityManager.getTransaction().begin();
        produto.setCategorias(Arrays.asList(categoria));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria check = entityManager.find(Categoria.class, categoria.getId());
//        Assert.assertFalse(check.getProdutos().isEmpty());
    }
}
