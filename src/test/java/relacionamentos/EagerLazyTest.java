package relacionamentos;

import model.Pedido;
import org.junit.Test;
import utilstest.EntityManagerTest;

public class EagerLazyTest extends EntityManagerTest {

    @Test
    public void testeComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);
        pedido.getItens().isEmpty();
    }
}
