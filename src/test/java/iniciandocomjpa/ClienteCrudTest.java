package iniciandocomjpa;

import model.Cliente;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static org.junit.Assert.*;
import static utilstest.UtilsTest.buildCliente;

public class ClienteCrudTest extends EntityManagerTest {

    @Test
    public void inserirCliente() {
        Cliente cliente = buildCliente();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Cliente verificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(verificacao);
    }

    @Test
    public void buscarCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        assertNotNull(cliente);
    }

    @Test
    public void atualizarCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        String nome = "Josefina";
        cliente.setNome(nome);
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Cliente verificacao = entityManager.find(Cliente.class, cliente.getId());
        assertEquals(nome, verificacao.getNome());
    }

    @Test
    public void removerCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente verificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNull(verificacao);
    }

}
