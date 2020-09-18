package mapeamentobasico;

import model.Cliente;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildCliente;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = buildCliente();

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente verificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals(cliente.getSexo(), verificacao.getSexo());

    }
}
