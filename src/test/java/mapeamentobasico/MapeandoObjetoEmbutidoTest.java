package mapeamentobasico;

import model.Cliente;
import model.Pedido;
import model.Produto;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utilstest.UtilsTest.buildCliente;
import static utilstest.UtilsTest.buildPedido;

public class MapeandoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void testarMapeamentoEmbutido() {
        Pedido pedido = buildPedido();
        Cliente cliente = buildCliente();
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido verificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(verificacao.getEnderecoEntrega());

    }
}
