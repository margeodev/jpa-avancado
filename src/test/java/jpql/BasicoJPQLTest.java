package jpql;

import com.sun.source.tree.AssertTree;
import model.Cliente;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "Select p from Pedido p where p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido p1 = typedQuery.getSingleResult();
        Assert.assertNotNull(p1);

        Query query = entityManager.createQuery(jpql);
        Pedido p2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(p2);
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(lista.get(0).getClass()));

        String jpql2 = "select p.cliente from Pedido p";
        TypedQuery<Cliente> typedQuery2 = entityManager.createQuery(jpql2, Cliente.class);
        List<Cliente> clientes = typedQuery2.getResultList();
        Assert.assertTrue(Cliente.class.equals(clientes.get(0).getClass()));
    }
}
