package jpql;

import dto.ProdutoDTO;
import model.Cliente;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void fazerJoin() {
        String jpql = "SELECT p, i FROM Pedido p JOIN p.itens i";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
