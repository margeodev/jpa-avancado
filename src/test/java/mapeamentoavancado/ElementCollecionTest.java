package mapeamentoavancado;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import utilstest.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

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
}
