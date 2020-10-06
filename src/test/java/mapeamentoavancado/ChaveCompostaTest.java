package mapeamentoavancado;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChaveCompostaTest extends EntityManagerTest {

    @Test
    public void salvarItem() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1L);
        Produto produto = entityManager.find(Produto.class, 1L);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        entityManager.persist(pedido);

        entityManager.flush();

        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setPedidoId(pedido.getId()); IdClass
//        itemPedido.setProdutoId(produto.getId()); IdClass
        itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(BigDecimal.ONE);

        entityManager.persist(itemPedido);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void bucarItem() {
        ItemPedido itemPedido = entityManager.find(
                ItemPedido.class, new ItemPedidoId(1L, 1L));

        Assert.assertNotNull(itemPedido);
    }

}
