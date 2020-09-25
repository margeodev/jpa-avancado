package mapeamentobasico.relacionamentos;

import model.Categoria;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void testeRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(itemPedido -> entityManager.remove(itemPedido));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        Pedido check = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(check);
    }
}
