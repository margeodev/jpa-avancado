package jpql;

import model.Pedido;
import model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class PassandoParametroTest extends EntityManagerTest {

    @Test
    public void passarParametro() {
        String jpql = "SELECT p FROM Pedido p JOIN p.pagamento pag WHERE p.id = :pedidoId AND pag.status = ?1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("pedidoId", 2L); // Passa parâmetro por string
        typedQuery.setParameter(1, StatusPagamento.PROCESSANDO); // Passa parâmetro por posição

//        Assert.assertFalse(lista.isEmpty());
    }
}
