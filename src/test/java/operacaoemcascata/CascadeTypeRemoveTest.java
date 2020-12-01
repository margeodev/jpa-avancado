package operacaoemcascata;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CascadeTypeRemoveTest extends EntityManagerTest {

     @Test
    public void removerItensOrfaos() {
        Pedido pedido = entityManager.find(Pedido.class, 1L);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
    }


}
