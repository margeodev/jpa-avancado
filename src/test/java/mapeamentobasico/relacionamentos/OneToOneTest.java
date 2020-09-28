package mapeamentobasico.relacionamentos;

import model.Cliente;
import model.PagamentoCartao;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.buildPagamentoCartao;
import static utilstest.UtilsTest.buildPedido;

public class OneToOneTest extends EntityManagerTest {

    @Test
    public void testeRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);

        PagamentoCartao pagamento = buildPagamentoCartao(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamento);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Pedido verificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertEquals(pagamento, verificacao.getPagamentoCartao());
    }
}
