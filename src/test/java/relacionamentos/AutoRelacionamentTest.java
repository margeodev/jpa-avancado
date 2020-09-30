package relacionamentos;

import model.Categoria;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

public class AutoRelacionamentTest extends EntityManagerTest {

    @Test
    public void testeRelacionamento() {
        Categoria pai = new Categoria();
        pai.setNome("Eletrônicos");

        Categoria filha = new Categoria();
        filha.setNome("Celular");
        filha.setCategoriaPai(pai);

        entityManager.getTransaction().begin();
        entityManager.persist(pai);
        entityManager.persist(filha);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria filhaCheck = entityManager.find(Categoria.class, filha.getId());
        Categoria paiCheck = entityManager.find(Categoria.class, pai.getId());

        Assert.assertNotNull(filhaCheck.getCategoriaPai());
//        Assert.assertFalse(paiCheck.getCategorias().isEmpty());
    }
}
