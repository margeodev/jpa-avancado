package mapeamentobasico.relacionamentos;

import model.Cliente;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildPedido;

public class EagerLazyTest extends EntityManagerTest {

    @Test
    public void testeComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);
        pedido.getItens().isEmpty();
    }
}
