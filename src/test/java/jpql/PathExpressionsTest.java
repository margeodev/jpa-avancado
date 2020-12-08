package jpql;

import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoEspecifico() {
        String jpql = "SELECT p FROM Pedido p JOIN p.itens i WHERE i.id.produtoId = 1";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarPathExpressions() {
        String jpql = "SELECT p.cliente.nome from Pedido p";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

}
