package mapeamentobasico.relacionamentos;

import model.Cliente;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;
import utilstest.UtilsTest;

import static utilstest.UtilsTest.buildCliente;
import static utilstest.UtilsTest.buildPedido;

public class ManyToOneTest extends EntityManagerTest {

    @Test
    public void testeRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        Pedido pedido = buildPedido();
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Pedido verificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertEquals(cliente, pedido.getCliente());
    }
}
