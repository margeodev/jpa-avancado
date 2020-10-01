package conhecendoentitymanager;

import model.Cliente;
import model.Pedido;
import model.Produto;
import model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildPedido;

public class ListenerTest extends EntityManagerTest {

    @Test
    public void acionarCallBack() {
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        Pedido pedido = buildPedido();
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Pedido check = entityManager.find(Pedido.class, pedido.getId());

    }

    @Test
    public void carregarEntidades() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);
        Produto produto = entityManager.find(Produto.class, 1L);
    }
}
