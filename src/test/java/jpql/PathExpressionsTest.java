package jpql;

import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {

    @Test
    public void usarPathExpressions() {
        String jpql = "SELECT p.cliente.nome from Pedido p";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
