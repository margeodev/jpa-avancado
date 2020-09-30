package conhecendoentitymanager;

import model.Cliente;
import org.junit.Test;
import utilstest.EntityManagerTest;

public class EstadosCicloVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        Cliente cliente = new Cliente(); // Estado Transient
        Cliente novo = entityManager.merge(cliente); // De transient para Managed
        Cliente cliente1 = entityManager.find(Cliente.class, 1L); // Estado Managed
        entityManager.remove(cliente1); // Estado Removed
        entityManager.persist(cliente1); // De Removed para Managed
        entityManager.detach(cliente1); // Estado Detached
    }
}
