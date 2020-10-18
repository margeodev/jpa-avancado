package mapeamentoavancado;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ElementCollecionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1L);
        produto.setTags(Arrays.asList("ebook", "livro digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto check = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(check.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1L);
        produto.setAtributos(Arrays.asList(new Atributo("tela", "800x600")));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto check = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(check.getAtributos().isEmpty());
    }

    @Test
    public void aplicarContato() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1L);
        cliente.setContatos(Collections.singletonMap("email", "fernando@email.com"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente check = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse(check.getContatos().isEmpty());
    }
}
