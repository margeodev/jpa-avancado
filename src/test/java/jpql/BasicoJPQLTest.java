package jpql;

import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
}
