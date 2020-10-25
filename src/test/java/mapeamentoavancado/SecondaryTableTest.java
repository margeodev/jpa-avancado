package mapeamentoavancado;

import model.Cliente;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildCliente;

public class SecondaryTableTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
        Cliente cliente = buildCliente();

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Cliente check = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertEquals(cliente.getSexo(), check.getSexo());
    }


}
