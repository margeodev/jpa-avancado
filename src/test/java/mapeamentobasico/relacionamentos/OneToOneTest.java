package mapeamentobasico.relacionamentos;

import model.Cliente;
import model.NotaFiscal;
import model.PagamentoCartao;
import model.Pedido;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import static utilstest.UtilsTest.*;

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

    @Test
    public void testeNotaFiscal() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);

        NotaFiscal notaFiscal = buildNotaFiscal(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();
        NotaFiscal verificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertEquals(pedido, verificacao.getPedido());
    }
}
